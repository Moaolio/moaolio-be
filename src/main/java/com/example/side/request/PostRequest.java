package com.example.side.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private String title;
    private List<String> tag;
    private String content;
    private String category;
    private Long userId;
    private Long communityId;

    private String postType; // "portfolio" 또는 "community"
    private String portfolioSpecificField; // 포트폴리오 포스트에 특화된 필드
    private String communitySpecificField; // 커뮤니티 포스트에 특화된 필드
}