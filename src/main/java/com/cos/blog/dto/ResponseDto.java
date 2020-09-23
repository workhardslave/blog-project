package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private int status;
    private T data;
}
