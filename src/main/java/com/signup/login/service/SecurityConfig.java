package com.signup.login.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 비밀번호 암호화 해서 저장하기
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Spring Security 기본 화면이 아닌
    // 내가 만든 페이지로 이동시키기
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http 
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/**",
                    "/",
                    "/login",
                    "/logout",
                    "/check/**",
                    "/signup",
                    "/check-email",
                    "/check/findpw",
                    "/check/findPw/sendEmail",
                    "/password",
                    "/ping"
                ).permitAll()

                // .anyRequest().authenticated()
                .anyRequest().permitAll()
            )

            // Spring Security 기본 로그인창 비활성화
            .formLogin(form -> form.disable())

            // logout 설정 추가
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            );

        return http.build();
    }
    
}
