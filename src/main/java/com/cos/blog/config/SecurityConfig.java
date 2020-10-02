package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // Bean 등록 (IoC) : 스프링 컨테이너에서 객체를 관리할 수 있게 하겠다.
@EnableWebSecurity // 시큐리티 필터 등록 : 스프링 시큐리티가 활성화 되어있는데, 어떤 설정을 해당 파일에 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll() // // /auth/** 로 오는 모든 요청은 모든 들어올 수 있다.
                .anyRequest()
                .authenticated() // 이를 제외한 모든 요청은 인증이 되어야만 들어올 수 있다.
            .and()
                .formLogin()
                .loginPage("/auth/signin"); // 로그인 페이지를 설정
    }
}
