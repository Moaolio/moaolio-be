package com.example.side.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostRequest {
    private String title;
    private String tag;
    private String content;
    private String category;
    private Long userId;
    private Long communityId;
}