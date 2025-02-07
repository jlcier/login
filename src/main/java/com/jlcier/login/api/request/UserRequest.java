package com.jlcier.login.api.request;

import com.jlcier.login.domain.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "role is required")
    private String role;
}
