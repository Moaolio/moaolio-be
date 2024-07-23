package com.example.side.user.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Long id;
    private String name;
    private String profile;
    private String Nickname;
    private String description;
    private String State;

    private String Kakao;
    private String Google;
    private String Naver;

    @CreatedDate
    private LocalDateTime Created;
    @LastModifiedDate
    private LocalDateTime Updated;
}
