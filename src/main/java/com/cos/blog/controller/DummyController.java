package com.cos.blog.controller;

import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController // html이 아닌 data를 리턴해주는 controller
public class DummyController {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    // http://localhost:8000/blog/dummy/join
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user) { // key - value (JSON)

        user.setRole(RoleType.ADMIN);

        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        System.out.println("role = " + user.getRole());
        System.out.println("createDate = " + user.getCreateDate());

        userRepository.save(user);
        return "회원가입 완료!";
    }

    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("dummy/user/{id}")
    public User detail(@PathVariable int id) { // {}안에 있는 변수와 같은 걸로 선언 해줘야 함

        // 만약 user/4를 찾으면 내가 DB에서 못찾으면 null이 뜨잖아
        // 그럼 return null이 되잖아? ... 좋지 못해 ㅠ.ㅠ
        // Optional로 User 객체를 감싸서 가져올게! null인지 확인해서 리턴해!
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });
        // 요청 : 웹 브라우저(클라이언트)
        // user 객체 : 자바 오브젝트
        // 웹 브라우저가 이해할 수 있는 형태로 변환 필요 -> JSON
        // 스프링부트에서는 MessageConverter가 응답시 자동으로 작동
        // 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson 라이브러리를 호출
        // user 객체를 JSON으로 변환해서 브라우저에게 전달한다.
        return user;

//        람다식
//        User user = userRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 유저는 없습니다. id : " + id));
//        return user;
    }
}
