package com.example.side.post.entity;

import com.example.side.post.tag.entity.PostTag;
import com.example.side.post.dto.request.PortfolioPostRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PortfolioPost extends Post {

    @Column(name = "url")
    private String url;
    @Column(name = "technology_stack")
    private String technologyStack;
    @Column(name = "img")
    private String img;



    public void update(PortfolioPostRequest portfolioPostRequest) {
        super.setTitle(portfolioPostRequest.getTitle());
        super.setDescription(portfolioPostRequest.getDescription());
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
//        this.img = portfolioPostRequest.getImg();
    }
    @Builder
    public PortfolioPost(String title, String description, String url, String technologyStack, String img) {
        super(title, description, null);
        this.url = url;
        this.technologyStack = technologyStack;
//        this.img = img;
    }
}