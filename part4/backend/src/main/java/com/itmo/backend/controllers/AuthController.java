package com.itmo.backend.controllers;

import com.itmo.backend.database.entity.AccountEntity;
import com.itmo.backend.database.repositories.AccountRepository;
import com.itmo.backend.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AccountRepository accountRepository;

    //@Autowired
    //private AuthenticationRequestValidator requestValidator;

    @PostMapping("/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequest data) {
        String email = data.getEmail();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPassword()));
            String token = jwtTokenProvider.createToken(email, new ArrayList<>() {{
                add("User");
            }});
            Map<Object, Object> model = new HashMap<>();
            model.put("id", accountRepository.findByEmail(email).orElseThrow(() ->
                    new BadCredentialsException("Email not found")).getId());
            model.put("token", token);
            logger.info("Вошёл пользователь с почтой: {}", email);
            return ok(model);
        } catch (AuthenticationException e) {
            logger.info("Попытка входа с почтой: {}. Ошибка: {}", email, e.getMessage());
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Map<Object, Object>> register(@RequestBody RegistrationRequest data) {
        String email = data.getEmail();
        if (accountRepository.findByEmail(email).isPresent() || accountRepository.findByPhone(data.getPhone()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        /*DataBinder validationManager = new DataBinder(data);
        validationManager.addValidators(requestValidator);
        validationManager.validate();
        if(validationManager.getBindingResult().hasErrors()){
            throw new BadCredentialsException(
                    validationManager.getBindingResult().getFieldError().getDefaultMessage());
        } else{*/
        AccountEntity newAccount = AccountEntity.builder().email(email)
                .password(new BCryptPasswordEncoder().encode(data.getPassword()))
                .phone(data.getPhone()).name(data.getName()).build();
        accountRepository.save(newAccount);
        logger.info("Зарегистрирован пользователь с почтой: {}", email);
        return login(data);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class AuthenticationRequest {

    protected String email;
    protected String password;
}

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
class RegistrationRequest extends AuthenticationRequest {

    private String phone;
    private String name;
}

class UserAlreadyExistException extends AuthenticationException {

    public UserAlreadyExistException() {
        super("User already exists");
    }
}