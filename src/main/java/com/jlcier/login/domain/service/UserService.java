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
        return user; // TODO
    }

    public User update(User user) {
        return repository.save(user);
    }

    public void delete(User user) {
        // TODO
    }

    public User login(User user) {
        return null; // TODO
    }
}
