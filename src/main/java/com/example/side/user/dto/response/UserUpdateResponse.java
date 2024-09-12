package com.example.side.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateResponse {

    private String nickname;
    private String introduction; // 자기소개
    private String contactInformation; // email
    private String experience; // 경력
    private String phone;
}
