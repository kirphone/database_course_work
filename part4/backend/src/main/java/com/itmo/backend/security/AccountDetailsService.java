package com.itmo.backend.security;

import com.itmo.backend.database.entity.AccountEntity;
import com.itmo.backend.database.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Email: " + email + "not found"));
        return new AccountPrincipal(account);
    }
}
