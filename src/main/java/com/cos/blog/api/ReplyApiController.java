package com.cos.blog.api;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "댓글 RestController")
@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ReplyService replyService;

    // 댓글 저장 API
    @ApiOperation(value = "댓글 저장", notes = "댓글을 저장합니다.")
    @PostMapping("/api/replies")
    public Long save(@RequestBody @Valid ReplySaveRequestDto dto) {

        System.out.println("ReplyApiController : save 호출");
        Long id = replyService.writeComment(dto);

        return id;

    }

    // 댓글 삭제 API
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제합니다.")
    @DeleteMapping("/api/replies/{id}")
    public Long delete(@PathVariable Long id) {

        System.out.println("ReplyApiController : remove 호출");
        replyService.deleteComment(id);

        return id;
    }
}
