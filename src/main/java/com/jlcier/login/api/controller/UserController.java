package com.jlcier.login.api.controller;

import com.jlcier.login.api.mapper.UserMapper;
import com.jlcier.login.api.request.UserAuthRequest;
import com.jlcier.login.api.request.UserRegisterRequest;
import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserRegisterRequest request, HttpSession session) {
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(new User())); // TODO
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthRequest request, HttpSession session) {
        return ResponseEntity.ok("Login successful"); // TODO
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
