package com.cos.blog.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.BoardSaveRequestDto;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        int id = boardService.writeForm(dto, principal.getUser());

        return id;

    }

    // 게시글 삭제 API
    @DeleteMapping("/api/boards/{id}")
    public int delete(@PathVariable int id) {

        System.out.println("BoardApiController : delete 호출");
        boardService.deleteABoard(id);

        return id;
    }

    // 게시글 수정 API
    @PutMapping("/api/boards/{id}")
    public int update(@PathVariable int id, @RequestBody @Valid BoardSaveRequestDto dto) {

        System.out.println("BoardApiController : update 호출");
        int uid = boardService.updateABoard(id, dto);

        return uid;
    }
}
