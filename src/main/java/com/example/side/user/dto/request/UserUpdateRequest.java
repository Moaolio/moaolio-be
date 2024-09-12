package com.example.side.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
public class UserUpdateRequest {

    private String uid;
    private String nickname;
    private String introduction; // 자기소개
    private String contactInformation; // email
    private String experience; // 경력

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "핸드폰 번호는 000-0000-0000 형식이어야 합니다.")
    private String phone;
}
