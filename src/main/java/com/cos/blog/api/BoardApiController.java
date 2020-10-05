package com.cos.blog.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.BoardSaveRequestDto;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;

    // 게시글 저장 API
    @PostMapping("/api/boards")
    public int save(@RequestBody @Valid BoardSaveRequestDto dto,
                    @AuthenticationPrincipal PrincipalDetail principal) {

        System.out.println("BoardApiController : save 호출");
        return boardService.writeForm(dto, principal.getUser());
    }
}
