package com.example.side.post.dto.response;

import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.tag.entity.PostTag;
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
    private String url; // Added field
    private List<CommentsResponse> comments;
    private Long likes;
    private Long views;

    public PortfolioPostResponse(PortfolioPost portfolioPost) {
        this.id = portfolioPost.getId();
        this.title = portfolioPost.getTitle();
        this.description = portfolioPost.getDescription();
        this.tag = portfolioPost.getTags().stream().map(PostTag::getName).collect(Collectors.toList());
        this.url = portfolioPost.getUrl();
        this.likes = portfolioPost.getLikeCount(); // Assuming likeCount is the correct field name
        this.comments = portfolioPost.getComments().stream()
                .map(CommentsResponse::new)
                .collect(Collectors.toList());
        this.views = portfolioPost.getViewCount(); // Assuming viewCount is the correct field name
    }
}
