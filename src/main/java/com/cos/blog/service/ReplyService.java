package com.cos.blog.service;

import com.cos.blog.domain.Board;
import com.cos.blog.domain.Reply;
import com.cos.blog.domain.User;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 생성자가 호출 될 때, 초기화 되지 않은 객체를 생성자 파라미터에 넣어서 초기화
@Service
public class ReplyService {

    // DI : @Autowired
//    private ReplyRepository replyRepository;
//    private BoardRepository boardRepository;
//    private UserRepository userRepository;
//
//    public ReplyService(ReplyRepository replyRepository, BoardRepository boardRepository, UserRepository userRepository) {
//        this.replyRepository = replyRepository;
//        this.boardRepository = boardRepository;
//        this.userRepository = userRepository;
//    }

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 댓글 저장 로직
    @Transactional
    public int writeComment(ReplySaveRequestDto dto) {

        Board board = boardRepository.findById(dto.getBid())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id : " + dto.getUid()));

        User user = userRepository.findById(dto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id :" + dto.getUid()));

        Reply reply = new Reply();
        reply.saveReply(board, user, dto.getContent());

        return replyRepository.save(reply).getId();
    }

    // 댓글 삭제 로직
    @Transactional
    public void deleteComment(int id) {

        System.out.println("id : " + id);
        replyRepository.deleteById(id);
    }
}
