package com.jlcier.login.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestSecurity {

    @GetMapping("/admin")
    public ResponseEntity<?> testAdmin() {
        return ResponseEntity.ok("Admin ok");
    }

    @GetMapping("/user")
    public ResponseEntity<?> testUser() {
        return ResponseEntity.ok("User ok");
    }
}
