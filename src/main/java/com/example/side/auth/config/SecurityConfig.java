package com.example.side.auth.config;

import com.example.side.auth.CustomOAuth2UserService;
import com.example.side.auth.CustomUserDetailsService;
import com.example.side.auth.handler.CustomSuccessHandler;
import com.example.side.auth.jwt.JWTFilter;
import com.example.side.auth.jwt.JWTUtil;
import com.example.side.auth.jwt.LoginFilter;
import com.example.side.auth.jwt.RefreshRepository;
import com.example.side.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationProvider;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        LoginFilter loginFilter = new LoginFilter(authenticationManager(), jwtUtil, refreshRepository);
        loginFilter.setFilterProcessesUrl("/api/user/login");

        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "Set-Cookie"));
                configuration.setExposedHeaders(Arrays.asList("Authorization", "Set-Cookie"));
                configuration.setMaxAge(3600L);

                return configuration;
            }
        }));


        http
                .csrf((auth) -> auth.disable())
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler))
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JWTFilter(jwtUtil, userRepository), LoginFilter.class)
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


}
