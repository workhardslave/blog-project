package com.cos.blog.dto;

import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class UserSaveRequestDto {

    private int id;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "메일의 양식을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @NotBlank(message = "패스워드를 입력해주세요.")
//    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    private RoleType role;

    public void giveRole(RoleType role) {
        this.role = role;
    }

    public void encodePassword(String password) {
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .username(username)
                .role(role)
                .build();
    }
}
