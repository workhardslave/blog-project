package com.cos.blog.api;

import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import com.cos.blog.dto.UserSaveRequestDto;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    // DI
    private final UserService userService;

//    @PostMapping("/api/users")
//    public ResponseDto<Integer> save(@RequestBody User user) {
//
//        System.out.println("save 호출됨");
//        user.setRole(RoleType.USER); // 나중에 리팩토링
//        userService.signUp(user);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

    @PostMapping("/api/users")
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

        return userService.signUp(dto);
    }
}
