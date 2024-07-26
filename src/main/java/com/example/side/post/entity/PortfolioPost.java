package com.example.side.post.entity;

import com.example.side.post.tag.entity.PostTag;
import com.example.side.user.entity.User;
import com.example.side.post.Dto.request.PortfolioPostRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "post")
public class PortfolioPost extends Post {

    private String url;
    private String technologyStack;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<PostTag> tags;

    public PortfolioPost() {}

    public PortfolioPost(PortfolioPostRequest portfolioPostRequest, User user) {
        super(portfolioPostRequest.getTitle(), portfolioPostRequest.getDescription(), user);
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
        // Assuming PortfolioPostRequest.getTags() returns List<String>
        this.tags = portfolioPostRequest.getTags().stream()
                .map(tagName -> new PostTag(tagName))
                .collect(Collectors.toList());
    }

    public void update(PortfolioPostRequest portfolioPostRequest) {
        super.setTitle(portfolioPostRequest.getTitle());
        super.setDescription(portfolioPostRequest.getDescription());
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
        // Assuming PortfolioPostRequest.getTags() returns List<String>
        this.tags = portfolioPostRequest.getTags().stream()
                .map(tagName -> new PostTag(tagName))
                .collect(Collectors.toList());
    }
}
