package com.cos.blog.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserUpdateRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "메일의 양식을 입력해주세요.")
    private String email;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;
}
