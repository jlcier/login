package com.jlcier.login.api.controller;

import com.jlcier.login.api.mapper.UserMapper;
import com.jlcier.login.api.request.UserRegisterRequest;
import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

//    private final UserService service;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> save(@RequestBody UserRegisterRequest request, HttpSession session) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(new User())); // TODO
//    }
}
