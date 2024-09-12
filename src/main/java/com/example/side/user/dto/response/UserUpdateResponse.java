package com.example.side.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserUpdateResponse {

    private String nickname;
    private String introduction; // 자기소개
    private List<String> myStack = new ArrayList<>(); // 기술스택 3가지
    private String contactInformation; // email
    private String experience; // 경력
    private String phone;
}
