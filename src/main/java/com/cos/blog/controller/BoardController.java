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

@RequiredArgsConstructor
@Controller
public class BoardController {

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수
    private final BoardService boardService;

    // 메인 페이지 : /WEB-INF/views/index.jsp
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> boards = boardService.findAllBoards(pageable);
        int tmp = boards.getPageable().getPageNumber() / BLOCK_PAGE_NUM_COUNT + 1;
        // 블록 시작 번호 : 1, 6, 11 ...
        int startPage = (tmp - 1) * BLOCK_PAGE_NUM_COUNT + 1;
        // 블록 끝 번호 : 5, 10, 15 ...
        int endPage = (boards.getTotalPages() == 0) ? 1 : Math.min(boards.getTotalPages(), tmp * BLOCK_PAGE_NUM_COUNT);

        System.out.println("tmp : " + tmp);
        System.out.println("s : " + startPage);
        System.out.println("e : " + endPage);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
    public String detailForm(@PathVariable int id,
                             @AuthenticationPrincipal PrincipalDetail principal, Model model) {

        model.addAttribute("board", boardService.findABoard(id));
        model.addAttribute("principals", principal); // 세션값 전달

        return "boards/detailForm";
    }

    // 글 수정 페이지
    @GetMapping("/boards/update/{id}")
    public String updateForm(@PathVariable int id, Model model) {

        model.addAttribute("board", boardService.findABoard(id));

        return "boards/updateForm";
    }
}
