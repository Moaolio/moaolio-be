package com.example.side.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioPostRequest {
    private Long id;
    private String title;
    private String description;
    private List<String> tag;
    private String url;
    private String technologyStack; // 사용된 기술 스택


    private String img; // 대표이미지
}