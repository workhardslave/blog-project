package com.cos.blog.api;

import com.cos.blog.dto.UserSaveRequestDto;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    // DI
    private final UserService userService;

//    private final HttpSession session;

    // 회원가입 API
    @PostMapping("/auth/api/signup")
    public int save(@RequestBody @Valid UserSaveRequestDto dto) { // @Valid 부분에서 유효성 검사가 일어나는듯?, 컨트롤러 안으로 안들어감

        System.out.println("UserApiController : save 호출");
        int id = userService.signUp(dto);

        return id;
    }

    // 로그인 API(전통적인 방식)
   /* @PostMapping("/api/users/signin")
    public int login(@RequestBody User user, HttpSession session) {

        System.out.println("UserApiController : login 호출됨");
        User principal = userService.signIn(user);

        if(principal != null) {
            session.setAttribute("principal", principal);
        }

        return principal.getId();

    }*/
}
