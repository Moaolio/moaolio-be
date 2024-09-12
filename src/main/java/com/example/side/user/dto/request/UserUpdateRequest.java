package com.example.side.user.dto.request;

import com.example.side.techStack.entity.TechStack;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserUpdateRequest {

    private String uid;
    private String nickname;
    private String introduction; // 자기소개

    @Size(max = 3, message = "기술 스택은 최대 3개까지만 가능합니다.")
    private List<String> myStack = new ArrayList<>(); // 기술스택 3가지

    private String contactInformation; // email
    private String experience; // 경력

    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "핸드폰 번호는 000-0000-0000 형식이어야 합니다.")
    private String phone;
}
