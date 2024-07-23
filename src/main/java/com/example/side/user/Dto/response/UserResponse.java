package com.example.side.user.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String profile;
    private String Nickname;
    private String description;
    private String State;

    private String Kakao;
    private String Google;
    private String Naver;

    private LocalDateTime Created;
    private LocalDateTime Updated;
}
