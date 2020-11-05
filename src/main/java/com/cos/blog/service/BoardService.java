package com.cos.blog.service;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.User;
import com.cos.blog.dto.BoardSaveRequestDto;
import com.cos.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class BoardService {

    private static final int BLOCK_PAGE_NUM_COUNT = 5;  // 블럭에 존재하는 페이지 번호 수

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

    // 페이징 블록 계산 로직
    public ArrayList<Integer> getPagingBlock(Page<Board> boards) {

        int tmp = boards.getPageable().getPageNumber() / BLOCK_PAGE_NUM_COUNT + 1;
        int startPage = (tmp - 1) * BLOCK_PAGE_NUM_COUNT + 1;  // 블록 시작 번호 : 1, 6, 11 ...
        int endPage = (boards.getTotalPages() == 0) ? 1
                : Math.min(boards.getTotalPages(), tmp * BLOCK_PAGE_NUM_COUNT); // 블록 끝 번호 : 5, 10, 15 ...

        System.out.println("tmp : " + tmp);
        System.out.println("s : " + startPage);
        System.out.println("e : " + endPage);

        ArrayList<Integer> block = new ArrayList<>();
        block.add(startPage);
        block.add(endPage);

        return block;
    }

    // 게시글 상세정보 로직
    @Transactional(readOnly = true)
    public Board findABoard(int id) {

        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id : " + id));
    }

    // 게시글 삭제 로직
    @Transactional
    public void deleteABoard(int id) {
        System.out.println("id = " + id);
        boardRepository.deleteById(id);
    }
    
    // 게시글 수정 로직
    @Transactional
    public int updateABoard(int id, BoardSaveRequestDto dto) {

        // 영속화
        Board board =  boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id : " + id));

        // 트랜잭션이 종료되면서, 영속화된 객체의 변화를 확인 -> 더티 체킹 -> update 쿼리 날라감
        board.updateBoard(dto.getTitle(), dto.getContent());

        return id;

    }
}
