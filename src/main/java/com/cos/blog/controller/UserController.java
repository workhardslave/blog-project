package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users/signInForm")
    public String signInForm() {

        return "users/signInForm";
    }

    @GetMapping("/users/signUpForm")
    public String signUpForm() {

        return "users/signUpForm";
    }


}
