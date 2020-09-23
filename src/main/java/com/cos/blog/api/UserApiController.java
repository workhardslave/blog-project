package com.cos.blog.api;

import com.cos.blog.domain.RoleType;
import com.cos.blog.domain.User;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    // DI
    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseDto<Integer> save(@RequestBody User user) {

        System.out.println("save 호출됨");
        user.setRole(RoleType.USER); // 나중에 리팩토링
        userService.signUp(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
