package com.cos.blog.api;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 저장 API
    @PostMapping("/api/replies")
    public int save(@RequestBody @Valid ReplySaveRequestDto dto) {

        System.out.println("ReplyApiController : save 호출");
        int id = replyService.writeComment(dto);

        return id;

    }

    @DeleteMapping("/api/replies/{id}")
    public int delete(@PathVariable int id) {

        System.out.println("ReplyApiController : remove 호출");
        replyService.deleteComment(id);

        return id;
    }
}
