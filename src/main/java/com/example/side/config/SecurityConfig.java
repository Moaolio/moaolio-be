package com.example.side.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable()); // 개발환경이기 때문에 잠시 꺼둠

        http
                .formLogin((login) -> login.disable());

        http
                .httpBasic((httpBasic) -> httpBasic.disable());

        http
                .oauth2Login(Customizer.withDefaults());

        http
                .authorizeHttpRequests((auth) -> auth // 경로 설정
                        .requestMatchers("/", "/api/user/**").permitAll()  // 이 경로에는 누구나 접근 가능
                        .anyRequest().authenticated()); // 나머지는 인증된 사용자만 접근가능

        return http.build();
    }
}
