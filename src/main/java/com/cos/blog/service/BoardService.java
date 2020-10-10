package com.cos.blog.service;

import com.cos.blog.domain.User;
import com.cos.blog.dto.BoardResponseDto;
import com.cos.blog.dto.BoardSaveRequestDto;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAllBoards() {
        return boardRepository.findAll().stream() // Board 객체를 가져와서
                .map(BoardResponseDto::new) // BoardResponseDto로 변환 하겠다.
                .collect(Collectors.toList()); // 바꾼 객체를 모아서 List 형태로 변환 하겠다.
    }

}
