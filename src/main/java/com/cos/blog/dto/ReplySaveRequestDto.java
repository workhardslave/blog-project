package com.cos.blog.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ReplySaveRequestDto {

    private int bid;
    private int uid;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
