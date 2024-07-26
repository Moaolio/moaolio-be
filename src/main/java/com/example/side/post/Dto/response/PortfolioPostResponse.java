package com.example.side.post.Dto.response;

import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.tag.entity.PostTag;
import com.example.side.comments.Dto.response.CommentsResponse;
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
    private List<String> tags; // Changed field name to plural 'tags'
    private String url; // Added field
    private List<CommentsResponse> comments;
    private Long likes;
    private Long views;

    public PortfolioPostResponse(PortfolioPost portfolioPost) {
        this.id = portfolioPost.getId();
        this.title = portfolioPost.getTitle();
        this.description = portfolioPost.getDescription();
        // Convert PostTag entities to a list of tag names (Strings)
        this.tags = portfolioPost.getTags().stream()
                .map(PostTag::getName) // Use getName() to match PostTag's field
                .collect(Collectors.toList());
        this.url = portfolioPost.getUrl();
        this.likes = portfolioPost.getLikeCount(); // Assuming likeCount is the correct field name
        this.comments = portfolioPost.getComments().stream()
                .map(CommentsResponse::new)
                .collect(Collectors.toList());
        this.views = portfolioPost.getViewCount(); // Assuming viewCount is the correct field name
    }
}
