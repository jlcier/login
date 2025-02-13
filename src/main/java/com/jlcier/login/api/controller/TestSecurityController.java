package com.jlcier.login.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestSecurityController {

    @GetMapping("/admin")
    public ResponseEntity<?> testAdmin() {
        return ResponseEntity.ok("Admin ok");
    }

    @GetMapping("/user")
    public ResponseEntity<?> testUser() {
        return ResponseEntity.ok("User ok");
    }

    @GetMapping("/login")
    public ResponseEntity<?> testLogin() {
        return ResponseEntity.ok("Logged in");
    }

    @GetMapping("")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("API Ready");
    }
}
