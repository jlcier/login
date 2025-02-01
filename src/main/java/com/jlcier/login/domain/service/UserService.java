package com.jlcier.login.domain.service;

import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return null; // TODO
    }
}
