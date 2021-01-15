package com.cos.blog.api;

import com.cos.blog.dto.UserSaveRequestDto;
import com.cos.blog.dto.UserUpdateRequestDto;
import com.cos.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "사용자 RestController")
@RequiredArgsConstructor
@RestController
public class UserApiController {

    // DI
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    // 회원가입 API
    @ApiOperation(value = "회원가입", notes = "회원가입을 진행합니다.")
    @PostMapping("/auth/api/signup")
    public Long save(@RequestBody @Valid UserSaveRequestDto dto) { // @Valid 부분에서 유효성 검사가 일어나는듯?, 컨트롤러 안으로 안들어감

        System.out.println("UserApiController : save 호출");
        Long id = userService.signUp(dto);

        return id;
    }

    // 회원 정보 수정 API
    @ApiOperation(value = "회원 정보수정", notes = "회원 정보수정을 진행합니다.")
    @PutMapping("/api/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDto dto) {

        System.out.println("UserApiController : update 호출");
        Long uid = userService.updateUser(id, dto);

        // 세션 수정
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return uid;
    }
}

// 로그인 API(전통적인 방식)

//    private final HttpSession session;

   /* @PostMapping("/api/users/signin")
    public int login(@RequestBody User user, HttpSession session) {

        System.out.println("UserApiController : login 호출됨");
        User principal = userService.signIn(user);

        if(principal != null) {
            session.setAttribute("principal", principal);
        }

        return principal.getId();

    }*/
