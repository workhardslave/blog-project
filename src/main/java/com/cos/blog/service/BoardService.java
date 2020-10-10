package com.cos.blog.service;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.User;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.BoardSaveRequestDto;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // 전체 게시물 가져 오는 로직 (페이징)
    @Transactional(readOnly = true)
    public Page<Board> findAllBoards(Pageable pageable) {

        return boardRepository.findAll(pageable);

    }

}
