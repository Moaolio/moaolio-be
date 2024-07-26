package com.example.side.post.Dto.request;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioPostRequest {
    private Long id;
    // Getter 및 Setter
    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private List<String> tags;
    @Setter
    @Getter
    private String url;
    @Setter
    @Getter
    private String technologyStack; // 사용된 기술 스택


    // 커스텀 생성자
    public PortfolioPostRequest(String title, String description, String url, String technologyStack, List<String> tags) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.technologyStack = technologyStack;
        this.tags = tags;
    }

}