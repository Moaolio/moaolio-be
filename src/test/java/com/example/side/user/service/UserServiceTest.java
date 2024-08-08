package com.example.side.user.service;

import com.example.side.auth.config.SecurityConfig;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.response.UserSignUpResponse;
import com.example.side.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void 회원가입_자체() throws Exception {
        //given
        UserSignUpRequest userDto = UserSignUpRequest.builder()
                .username("User1")
                .password("123456")
                .email("email@email.com")
                .name("name")
                .birth("111111")
                .build();

        //when
        UserSignUpResponse userSignUpResponse = userService.signUp(userDto);

        //then
        assertThat(userSignUpResponse.getUsername()).isEqualTo("User1");

    }
}