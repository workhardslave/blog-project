package com.cos.blog.dto;


import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class UserResponseDto {

    private int id;
    private String email;
    private String username;
    private String password;
    private RoleType role;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

}
