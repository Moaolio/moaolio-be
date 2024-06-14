package com.example.side.response;

import com.example.side.model.entity.UserPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostResponse {
    private Long id;
    private String title;
    private String tag;
    private String content;
    private String category;
    private Long likeCount;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
    private Long communityId;

    private List<UserScrapResponse> userScrapList;
    private List<UserPostFileResponse> userPostFileList;
    private List<UserPostCommentsResponse> userPostCommentsList;
    private List<PostLikeResponse> postLikeList;

    public UserPostResponse(UserPost userPost) {
        this.title = userPost.getTitle();
        this.tag = userPost.getTag();
        this.content = userPost.getContent();
        this.category = userPost.getCategory();
        this.createdAt = userPost.getCreatedAt();
        this.userId = userPost.getUser().getId();
    }
}