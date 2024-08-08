package com.example.side.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserPasswordFindResponse {
    private String password;
}
