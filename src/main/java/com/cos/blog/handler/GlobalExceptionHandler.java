package com.cos.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 전역적으로 예외처리
@RestController
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public String handlerException(Exception e) {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }
}
