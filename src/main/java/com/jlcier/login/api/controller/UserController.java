package com.jlcier.login.api.controller;

import com.jlcier.login.api.mapper.UserMapper;
import com.jlcier.login.api.request.UserAuthRequest;
import com.jlcier.login.api.request.UserRegisterRequest;
import com.jlcier.login.config.TokenService;
import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmation())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Password confirmation doesn't match");
        }
        User user = UserMapper.toUser(request);
        if (service.register(user) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username taken");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        User user = service.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }
}
