package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // /WEB-INF/views/index.jsp
    @GetMapping({"","/"})
    public String index(Model model) {

        model.addAttribute("boards", boardService.findAllBoards());
        return "index";
    }

    @GetMapping("/boards/form")
    public String writeForm() {

        return "boards/writeForm";
    }
}
