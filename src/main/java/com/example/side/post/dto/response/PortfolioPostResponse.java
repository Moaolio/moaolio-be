package com.example.side.post.dto.response;

import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.tag.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioPostResponse {
    private Long id;
    private String title;
    private String description;
    private List<String> tag;
    private String url;
    private List<CommentsResponse> comments;
    private Long likes;
    private Long views;

    public PortfolioPostResponse(PortfolioPost portfolioPost) {
        this.id = portfolioPost.getId();
        this.title = portfolioPost.getTitle();
        this.description = portfolioPost.getDescription();
        this.tag = portfolioPost.getTags().stream().map(postTag -> postTag.getTag().getName()).collect(Collectors.toList());
        this.url = portfolioPost.getUrl();
        this.likes = portfolioPost.getLikeCount();
        this.comments = portfolioPost.getComments().stream()
                .map(CommentsResponse::new)
                .collect(Collectors.toList());
        this.views = portfolioPost.getViewCount();
    }

    public static PortfolioPostResponse of(PortfolioPost portfolioPost, List<CommentsResponse> commentsResponses, boolean b) {
        return PortfolioPostResponse.builder()
                .id(portfolioPost.getId())
                .title(portfolioPost.getTitle())
                .description(portfolioPost.getDescription())
                .tag(portfolioPost.getTags().stream().map(postTag -> postTag.getTag().getName()).collect(Collectors.toList()))
                .url(portfolioPost.getUrl())
                .likes(portfolioPost.getLikeCount())
                .views(portfolioPost.getViewCount())
                .comments(commentsResponses)
                .build();
    }
}