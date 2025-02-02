package com.jlcier.login.api.mapper;

import com.jlcier.login.api.request.UserAuthRequest;
import com.jlcier.login.api.request.UserRegisterRequest;
import com.jlcier.login.api.response.UserResponse;
import com.jlcier.login.domain.entity.User;

public class UserMapper {

    public static User toUser(UserRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    public static User toUser(UserAuthRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        return response;
    }

}
