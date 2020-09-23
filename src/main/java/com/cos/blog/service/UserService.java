package com.cos.blog.service;

import com.cos.blog.domain.User;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록 (IoC)
@RequiredArgsConstructor // 초기화되지 않은 final 필드나, @NonNull이 붙은 필드에 생성자를 생성
public class UserService {

    // 어떠한 Bean에 생성자가 오직 하나만 있고, 생성자의 파라미터로 받는 Bean이 존재하면
    // @Autowired 어노테이션을 명시하지 않아도 DI 가능!
    private final UserRepository userRepository;

    // 회원가입 로직
    @Transactional
    public void signUp(User user) {

        userRepository.save(user);
    }
}
