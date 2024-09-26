package com.example.side.post.dto.response;

import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.post.entity.CommunityPost;
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

public class CommunityPostResponse  {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Long likes;
    private Long views;
    private List<CommentsResponse> comments; // 추가된 필드


    public CommunityPostResponse(CommunityPost communityPost) {
        this.id = communityPost.getId();
        this.title = communityPost.getTitle();
        this.description = communityPost.getDescription();
//        this.category = communityPost.getCategory().toString();
        this.likes = communityPost.getLikeCount();
        this.views = communityPost.getViewCount();
        this.comments = communityPost.getComments().stream()
                .map(CommentsResponse::new)
                .collect(Collectors.toList());
    }
    public static CommunityPostResponse of(CommunityPost communityPost, List<CommentsResponse> commentsResponses, boolean b) {
        return CommunityPostResponse.builder()
                .id(builder().id)
                .comments(commentsResponses)
                .build();
    }
    public static CommunityPostResponse from(CommunityPost communityPost) {
        CommunityPostResponse response = new CommunityPostResponse();
        response.setId(communityPost.getId());
        response.setTitle(communityPost.getTitle());
        response.setDescription(communityPost.getDescription());
        response.setLikes(communityPost.getLikeCount());
        response.setViews(communityPost.getViewCount());
        return response;
    }
}
