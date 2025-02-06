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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        if (service.register(UserMapper.toUser(request)) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username taken");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthRequest request) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Authentication auth = authManager.authenticate(usernamePassword);
        return ResponseEntity.ok(tokenService.generateToken((User) auth.getPrincipal()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        User user = service.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exists");
        }
        service.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping
    public ResponseEntity<?> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toUserResponseList(service.listAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UserRegisterRequest request) {
        if (service.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exists");
        }
        User user = service.update(id, UserMapper.toUser(request));
        return ResponseEntity.ok(UserMapper.toUserResponse(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRegisterRequest request) {
        if (service.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exists");
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (!currentUser.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can only update your own profile");
        }

        request.setRole(currentUser.getRole().getRole());
        return ResponseEntity.ok(UserMapper.toUserResponse(service.update(id, UserMapper.toUser(request))));
    }
}
