package com.example.side.model.entity;

import com.example.side.request.PostRequest;

/*
태그, 카테고리로 나눔
 */
public class PortfolioPost extends Post{
    private String portfolioSpecificField; // 포트폴리오 포스트에 특화된 필드 예시

    public PortfolioPost(PostRequest postRequest, User user) {
        super(postRequest, user);
        this.portfolioSpecificField = postRequest.getPortfolioSpecificField();
    }
}
