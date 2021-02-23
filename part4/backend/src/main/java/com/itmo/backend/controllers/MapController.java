package com.itmo.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/map")
public class MapController {

    @GetMapping("/api_key")
    public ResponseEntity<String> getApiKey() {
            return ok(System.getenv("GOOGLE_MAP_API_KEY"));
    }
}
