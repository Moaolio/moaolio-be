package com.example.side.auth.jwt;

import com.example.side.auth.CustomOAuth2User;
import com.example.side.user.dto.request.UserOAuth2Dto;
import com.example.side.user.entity.UserRole;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /**
         * 쿠키에서 토큰 찾기
         */
        String authorization = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization")) {
                authorization = cookie.getValue();
            }
        }

        /**
         * 토큰 없다면 다음 필터로 데이터 넘기기
         */
        if (authorization == null) {
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * 만료 토큰이면 다음 필터로 데이터 넘기기
         */
        String token = authorization;
        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * 토큰에서 username, role 획득 후 값 매핑
         */
        String username = jwtUtil.getUsername(token);
        String userRole = jwtUtil.getRole(token);
        UserOAuth2Dto userOAuth2Dto = new UserOAuth2Dto();
        userOAuth2Dto.setUsername(username);
        userOAuth2Dto.setRole(userRole);

        /**
         * OAuth2UserDetails에 회원 정보 객체 담기
         */
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userOAuth2Dto);

        /**
         * 스프링 시큐리티 인증 토큰 생성
         */
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        /**
         * 세션에 사용자 등록
         */
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
