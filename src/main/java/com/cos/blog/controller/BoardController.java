package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.domain.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // 메인 페이지 : /WEB-INF/views/index.jsp
    @GetMapping({"", "/"})
    public String index(Model model, @RequestParam(required = false, defaultValue = "") String category,
                        @RequestParam(required = false, defaultValue = "") String searchText,
                        @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> boards = boardService.findAllBoards(category, searchText, pageable);
        ArrayList block = boardService.getPagingBlock(boards);

        model.addAttribute("startPage", block.get(0));
        model.addAttribute("endPage", block.get(1));
        model.addAttribute("boards", boards);

        return "index";
    }

    // 글쓰기 화면 페이지
    @GetMapping("/boards/form")
    public String writeForm() {

        return "boards/writeForm";
    }

    // 상세정보 페이지
    @GetMapping("/boards/detail/{id}")
    public String detailForm(@PathVariable Long id,
                             @AuthenticationPrincipal PrincipalDetail principal, Model model) {

        model.addAttribute("board", boardService.findABoard(id));
        model.addAttribute("principals", principal); // 세션값 전달

        return "boards/detailForm";
    }

    // 글 수정 페이지
    @GetMapping("/boards/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {

        model.addAttribute("board", boardService.findABoard(id));

        return "boards/updateForm";
    }
}
