package com.cos.blog.controller;

import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController // html이 아닌 data를 리턴해주는 controller
public class DummyController {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    // http://localhost:8000/blog/dummy/join
    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user) { // key - value (JSON)

//        user.setRole(RoleType.USER);

        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());
        System.out.println("role = " + user.getRole());

        userRepository.save(user);
        return "회원가입 완료!";
    }

    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/users/3
    @GetMapping("dummy/users/{id}")
    public User detail(@PathVariable Long id) { // {}안에 있는 변수와 같은 걸로 선언 해줘야 함

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

    //http://localhost:8000/blog/dummy/users
    @GetMapping("/dummy/users")
    public List<User> list() {

        return userRepository.findAll();
    }

    //http://localhost:8000/blog/dummy/users/lists
    @GetMapping("/dummy/users/lists")
    // size : 한 페이지당 보여줄 게시물, sort : 분류 기준, direction : 정렬
    public List<User> pageList(@PageableDefault(size = 2, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable) {

        Page<User> pagingUsers = userRepository.findAll(pageable);
        List<User> users = pagingUsers.getContent();

        return users;
    }

    // save() 함수는 id를 전달 하지 않으면 insert
    // id를 전달 하면 해당 id에 대한 데이터가 있으면 update (권장 방식 X)
    // id를 전달 하고 해당 id에 대한 데이터가 있으면 insert

    // http://localhost:8000/blog/dummy/users/3
    // update 상황에서 @Transactional 어노테이션을 명시하면, save()를 사용하지 않아도 update 쿼리가 날라감
    @Transactional // 함수 종료시에 자동 commit
    @PutMapping("/dummy/users/{id}")
    // @ReqeustBody : JSON 데이터 요청 -> Java Object(Message Converter의 Jackson 라이브러리가 변환해줌)
    public User updateUser(@PathVariable Long id, @RequestBody User userRequest) {
        System.out.println("id = " + id);
        System.out.println("userRequest.getEmail() = " + userRequest.getEmail());
        System.out.println("userRequest.getPassword() = " + userRequest.getPassword());
        userRequest.updateUser(userRequest.getEmail(),userRequest.getPassword());

        // 영속화
        User user = userRepository.findById(id).orElseThrow(() ->
            new IllegalArgumentException("해당 유저는 없습니다. id : " + id));

        // 변경이 일어남 (영속화된 객체에서 변경이 일어나면 더티 체킹 발생)
        user.updateUser(userRequest.getEmail(), userRequest.getPassword());
//        userRepository.save(user);

        return userRequest;
    }

    // http://localhost:8000/blog/dummy/users/1
    @DeleteMapping("dummy/users/{id}")
    public String delete(@PathVariable Long id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제 실패";
        }
        return "삭제 완료 id : " + id;
    }
}
