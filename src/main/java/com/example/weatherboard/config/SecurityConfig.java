package com.example.weatherboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.weatherboard.config.handler.LoginAuthFailureHandler;
import com.example.weatherboard.config.handler.LoginAuthSuccessHandler;
import com.example.weatherboard.config.handler.LogoutAuthSuccesshandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    
    //비번 암호화 도구
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;
    @Autowired
    private LoginAuthFailureHandler loginAuthFailureHandler;
    @Autowired
    private LogoutAuthSuccesshandler logoutAuthSuccesshandler;

    //인증(로그인), 인가(권한)에 대한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf(AbstractHttpConfigurer::disable);

        //인증 인가 설정
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/member/**")
                    //인증만 접근 가능
                    .authenticated()
                .requestMatchers("/admin/**")
                    .hasAnyAuthority("ADMIN")
                .anyRequest().permitAll()
            )

            .formLogin(formLogin -> formLogin
                //멤버 컨트롤러에서 이거 써야 함
                .loginPage("/loginForm")
                //로그인 화면에서 form태그의 action 주소
                //컨트롤러에서 정의할 필요 없음
                .loginProcessingUrl("/login")
                //로그인 성공 시 접속할 url 주소
                //멤버 컨트롤러에서 이거 써야 함
                .successHandler(loginAuthSuccessHandler)
                .failureHandler(loginAuthFailureHandler)
                .permitAll()
            )
            // 로그아웃에 대한 설정 
            .logout(logout -> logout
                // 로그아웃 요청 url path 
                .logoutUrl("/logout")
                // 로그아웃 성공시
                .logoutSuccessHandler(logoutAuthSuccesshandler)
                .permitAll()
            );

        // 위에서 설정한 인증 & 인가를 Spring Boot Configuration에 적용!!
        return http.build();
    }
}
