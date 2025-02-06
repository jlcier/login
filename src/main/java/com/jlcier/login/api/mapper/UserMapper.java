package com.jlcier.login.api.mapper;

import com.jlcier.login.api.request.UserAuthRequest;
import com.jlcier.login.api.request.UserRegisterRequest;
import com.jlcier.login.api.response.UserResponse;
import com.jlcier.login.domain.entity.User;
import com.jlcier.login.domain.entity.UserRole;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));
        return user;
    }

//    public static User toUser(UserAuthRequest request) {
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        return user;
//    }

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().getRole());
        return response;
    }

    public static List<UserResponse> toUserResponseList(List<User> users) {
        return users.stream()
            .map(UserMapper::toUserResponse)
            .collect(Collectors.toList());
    }
}
