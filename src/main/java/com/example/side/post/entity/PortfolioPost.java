package com.example.side.post.entity;

import com.example.side.post.tag.entity.PostTag;
import com.example.side.user.entity.User;
import com.example.side.post.Dto.request.PortfolioPostRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class PortfolioPost extends Post {

    private String url; // 작업물 URL
    private String technologyStack; // 사용된 기술 스택

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "portfolio_post_tags",
            joinColumns = @JoinColumn(name = "portfolio_post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<PostTag> tags;

    // 기본 생성자
    public PortfolioPost() {}

    // 커스텀 생성자
    public PortfolioPost(PortfolioPostRequest portfolioPostRequest, User user) {
        super(portfolioPostRequest.getTitle(), portfolioPostRequest.getDescription(), user);
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
        this.setDescription(portfolioPostRequest.getDescription());
    }

    // 업데이트 메서드
    public void update(PortfolioPostRequest portfolioPostRequest) {
        super.setTitle(portfolioPostRequest.getTitle());
        super.setDescription(portfolioPostRequest.getDescription());
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
    }
}
