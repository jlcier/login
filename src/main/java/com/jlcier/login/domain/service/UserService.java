package com.jlcier.login.domain.service;

import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User update(Long id, User user) {
        user.setId(id);
        if (user.getPassword() == null) {
            user.setPassword(findById(id).getPassword());
        }
        return save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public User register(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            return null;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return save(user);
    }

    public List<User> listAll() {
        return repository.findAll();
    }

    public User changePassword(User user, String currentPassword, String newPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(currentPassword, user.getPassword())) {
            return null;
        }
        user.setPassword(encoder.encode(newPassword));
        return save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
