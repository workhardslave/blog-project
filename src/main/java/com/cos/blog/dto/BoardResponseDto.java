package com.cos.blog.dto;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.User;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private int id;
    private String title;
    private String content;
    private int count;
    private User user;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.count = board.getCount();
        this.user = board.getUser();
    }

}
