package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.config.oauth.PrincipalOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // Bean 등록 (IoC) : 스프링 컨테이너에서 객체를 관리할 수 있게 하겠다.
@EnableWebSecurity // 시큐리티 필터 등록 : 스프링 시큐리티가 활성화 되어있는데, 어떤 설정을 해당 파일에 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;
    private final PrincipalOAuth2UserService principalOauth2UserService;

    @Override @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encoderPWD() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인해주는데 password를 가로채기 하는데
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable() // csrf 토큰 비활성화
            .authorizeRequests()
                .antMatchers("/", "/auth/**", "/static/**", "/js/**", "/css/**", "/image/**")
                .permitAll() // // /auth/** 로 오는 모든 요청은 모든 들어올 수 있다.
                .anyRequest()
                .authenticated() // 이를 제외한 모든 요청은 인증이 되어야만 들어올 수 있다.
            .and()
                .formLogin()
                .loginPage("/auth/signin") // 로그인 페이지를 설정
                .loginProcessingUrl("/auth/api/signin") // 스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인
                .defaultSuccessUrl("/") // 로그인이 정상적으로 작동되면 해당 주소로 이동
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            .and()
                .oauth2Login()
                .loginPage("/auth/signin")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
    }
}
