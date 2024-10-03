package com.example.side.post.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioPostRequest {
    private String title;
    private String description;
    private List<String> tags;
    private String url;
    private String technologyStack; // 사용된 기술 스택
    private Boolean postType; // 0. 포트폴리오 전용 1. 커뮤니티 전용

    private String img;


}