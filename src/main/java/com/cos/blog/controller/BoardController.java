package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // 메인 페이지 : /WEB-INF/views/index.jsp
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("boards", boardService.findAllBoards(pageable));

        return "index";
    }

    // 글쓰기 화면 페이지
    @GetMapping("/boards/form")
    public String writeForm() {

        return "boards/writeForm";
    }

    // 상세정보 페이지
    @GetMapping("/boards/detail/{id}")
    public String detailForm(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetail principal) {

        model.addAttribute("board", boardService.findABoard(id));
        model.addAttribute("principal", principal); // 세션값 전달

        return "boards/detailForm";
    }

    // 글 수정 페이지
    @GetMapping("/boards/update/{id}")
    public String updateForm(@PathVariable int id, Model model) {

        model.addAttribute("board", boardService.findABoard(id));

        return "boards/updateForm";
    }
}
