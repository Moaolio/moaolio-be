package com.example.side.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignUpResponse {

    private String uid;
    private String email;
    private String name;
    private String birth;

}
