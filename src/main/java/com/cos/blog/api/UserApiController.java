package com.cos.blog.api;

import com.cos.blog.dto.UserSaveRequestDto;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    // DI
    private final UserService userService;

//    private final HttpSession session;

    // 회원가입 API
    @PostMapping("/auth/api/signup")
    // @Valid 부분에서 유효성 검사가 일어나는듯?, 컨트롤러 안으로 안들어감
    public int save(@RequestBody @Valid UserSaveRequestDto dto) {
        // 회원가입 실패시, 입력 데이터를 유지
//        if(errors.hasErrors()) {
//            model.addAttribute("dto",dto);
//
//            // 유효성 통과 못한 필드와 메세지를 핸들링
//            Map<String, String> valid = userService.validateHandling(errors);
//            for(String key : valid.keySet()) {
//                model.addAttribute(key, valid.get(key));
//            }
//
//            return;
//        }
        System.out.println("UserApiController : save 호출됨");
        return userService.signUp(dto);
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
