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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // 초기화되지 않은 final 필드나, @NonNull이 붙은 필드에 생성자를 생성
@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록 (IoC)
public class UserService {

    // 어떠한 Bean에 생성자가 오직 하나만 있고, 생성자의 파라미터로 받는 Bean이 존재하면
    // @Autowired 어노테이션을 명시하지 않아도 DI 가능!
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    // 회원가입 시, 유효성 체크
//    public Map<String, String> validateHandling(Errors errors) {
//        Map<String, String> validatorResult = new HashMap<>();
//
//        // errors.getFieldError() : 유효성 검사에서 실패한 필드 목록을 가져옴
//        // error.getField() : 유효성 검사에 실패한 필드명을 가져옴
//        // error.getDefaultMessage() : 유효성 검사에 실패한 필드에 정의된 메세지를 가져옴
//        for(FieldError error : errors.getFieldErrors()) {
//            String validKeyName = String.format("valid_%s", error.getField());
//            validatorResult.put(validKeyName, error.getDefaultMessage());
//        }
//
//        return validatorResult;
//    }

    // 회원가입 로직
    @Transactional
    public int signUp(UserSaveRequestDto dto) {

        System.out.println("UserService : signUp 호출");
        String rawPassword = dto.getPassword();
        String encPassword = encoder.encode(rawPassword);
        dto.giveRole(RoleType.USER); // USER, ADMIN
        dto.encodePassword(encPassword); // 해쉬화 된 비밀번호 저장

        return userRepository.save(dto.toEntity()).getId();
    }

    // 회원수정 로직
    @Transactional
    public int updateUser(int id, UserUpdateRequestDto dto) {

        System.out.println("UserService : updateUser 호출");

        // 영속화
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. : " + id));

        String rawPassword = dto.getPassword();
        String encPassword = encoder.encode(rawPassword);

        user.updateUser(dto.getEmail(), encPassword);

        return user.getId();

    }


}

 /* @Transactional(readOnly = true) // Select할 때, 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    public User signIn(User user) {

        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }*/