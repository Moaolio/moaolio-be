package com.example.side.post.entity;

import com.example.side.post.tag.entity.PostTag;
import com.example.side.user.entity.User;
import com.example.side.post.dto.request.PortfolioPostRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class PortfolioPost extends Post {

    private String url;
    private String technologyStack;
    private String img;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<PostTag> tags; // Ensure correct type

    public PortfolioPost() {}

    public PortfolioPost(PortfolioPostRequest portfolioPostRequest, User user) {
        super(portfolioPostRequest.getTitle(), portfolioPostRequest.getDescription(), user);
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
        this.img = portfolioPostRequest.getImg();
    }

    public void update(PortfolioPostRequest portfolioPostRequest) {
        super.setTitle(portfolioPostRequest.getTitle());
        super.setDescription(portfolioPostRequest.getDescription());
        this.url = portfolioPostRequest.getUrl();
        this.technologyStack = portfolioPostRequest.getTechnologyStack();
        this.img = portfolioPostRequest.getImg();
    }

    public void setRepresentativeImageUrlAutomatically(String url) {
        this.img = url;
    }
}