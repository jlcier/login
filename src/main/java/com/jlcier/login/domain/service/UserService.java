package com.jlcier.login.domain.service;

import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

//    public User update(User user) {
//        return repository.save(user);
//    }

//    public void delete(User user) {
//        // TODO
//    }

    public User register(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return save(user);
    }

//    public User login(User user) {
//        return null; // TODO
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
