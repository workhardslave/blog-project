package com.cos.blog.dto;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private int id;
    private String title;
    private String content;
    private int count;
    private User user;

    @Builder
    public BoardResponseDto(int id, String title, String content, int count, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
    }

}
