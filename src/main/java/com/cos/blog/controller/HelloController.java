package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String helloTest() {
        System.out.println("작동");
        return "helloTest";
    }
}
