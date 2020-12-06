package com.cos.blog.dto;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class BoardSaveRequestDto {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private String content;

    private int count;

    private User user;

    public void giveUser(User user) {
        this.user = user;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .count(count)
                .user(user)
                .build();
    }
}
