package com.itmo.backend.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/map")
public class MapController {

    @Value("${api_key}")
    private String API_KEY;

    @GetMapping("/api_key")
    public ResponseEntity<String> getApiKey() throws IOException {
        Optional<String> api_key = Optional.ofNullable(System.getenv("GOOGLE_MAP_API_KEY"));
        return ok(api_key.orElseGet(() -> API_KEY));
    }
}
