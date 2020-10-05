package com.cos.blog.config.auth;

import com.cos.blog.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고, 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {

    private User user; // 컴포지션 : 객체를 품고 있는 것

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // 계정이 만료 되어 있는지 리턴 (true : 만료X, false : 만료O)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨 있는지 리턴 (true : 잠김O, false : 잠김X)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀 번호가 만료 되어 있는지 리턴 (true : 만료X, false : 만료O)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 되어 있는지 리턴 (true : 활성O, false : 활성X)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정이 갖고 있는 권한 목록을 리턴 한다. (권한이 여러개 일 경우 루프를 돌아야 한다.)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(new GrantedAuthority() {
//
//            @Override
//            public String getAuthority() {
//                return "ROLE_" + user.getRole(); // ROLE_USER
//            }
//        });

        // 람다식
        collectors.add(() -> "ROLE_" + user.getRole());

        return collectors;
    }
}
