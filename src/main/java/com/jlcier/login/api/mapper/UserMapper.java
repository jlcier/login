package com.jlcier.login.api.mapper;

import com.jlcier.login.api.request.UserAuthRequest;
import com.jlcier.login.api.request.UserRegisterRequest;
import com.jlcier.login.api.response.UserResponse;
import com.jlcier.login.domain.entity.User;

public class UserMapper {

    public static User toUser(UserRegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        return user;
    }

    public static User toUser(UserAuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        return response;
    }

}
