package com.example.side.user.scrap.Dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserScrapRequest {
    private Long userPostId;
    private Long userId;
}