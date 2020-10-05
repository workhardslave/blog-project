package com.cos.blog.service;

import com.cos.blog.domain.User;
import com.cos.blog.dto.BoardSaveRequestDto;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    // 게시글 저장 로직
    @Transactional
    public int writeForm(BoardSaveRequestDto dto, User user) {

        System.out.println("BoardService : writeForm 호출");
        dto.giveUser(user);

        return boardRepository.save(dto.toEntity()).getId();
    }

}
