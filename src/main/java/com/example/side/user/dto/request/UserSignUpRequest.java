package com.example.side.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 자체 회원 가입 유저 DTO
 */
@Builder
@Getter
@Setter
public class UserSignUpRequest {

    private String username;
    private String password;
    private String email;
    private String name;
    private String nickName;

    /**
     * JOB, TECH, CAREER, BIRTH
     */
}
