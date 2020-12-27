package com.cos.blog.service;

import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import com.cos.blog.dto.UserSaveRequestDto;
import com.cos.blog.dto.UserUpdateRequestDto;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 초기화되지 않은 final 필드나, @NonNull이 붙은 필드에 생성자를 생성
@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록 (IoC)
public class UserService {

    // 어떠한 Bean에 생성자가 오직 하나만 있고, 생성자의 파라미터로 받는 Bean이 존재하면
    // @Autowired 어노테이션을 명시하지 않아도 DI 가능!
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    // 회원가입 로직
    @Transactional
    public Long signUp(UserSaveRequestDto dto) {

        System.out.println("UserService : signUp 호출");
        System.out.println("rawPassword : " + dto.getPassword());
        String rawPassword = dto.getPassword();
        String encPassword = encoder.encode(rawPassword);
        dto.giveRole(RoleType.USER); // USER, ADMIN
        dto.encodePassword(encPassword); // 해쉬화 된 비밀번호 저장

        return userRepository.save(dto.toEntity()).getId();
    }

    // 회원찾기 로직
//    @Transactional(readOnly = true)
//    public User findByEmail(String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseGet(() -> new User());
//
//        return user;
//    }

    // 회원수정 로직
    @Transactional
    public Long updateUser(Long id, UserUpdateRequestDto dto) {

        System.out.println("UserService : updateUser 호출");

        // 영속화
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. : " + id));

        // 일반 사용자만 이메일, 패스워드 변경
        if(user.getProvider() == null || user.getProvider().equals("")) {
            String rawPassword = dto.getPassword();
            String encPassword = encoder.encode(rawPassword);
            user.updateUser(dto.getEmail(), encPassword);
        }

        return user.getId();

    }

}

 /* @Transactional(readOnly = true) // Select할 때, 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    public User signIn(User user) {

        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }*/