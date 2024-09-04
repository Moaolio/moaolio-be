package com.example.side.user.dto.request;

import lombok.Getter;
import lombok.Setter;


/**
 * OAuth2 회원 가입 유저 DTO
 */

@Getter @Setter
public class UserOAuth2Dto {

    private String uid; // username -> 리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디
    private String nickname; // OAuth2에서 제공받는 이름(실명) -> 추후에 변경
    private String role;

}
