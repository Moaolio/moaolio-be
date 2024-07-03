package com.example.side.response;

import com.example.side.model.entity.Post;
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
public class PostResponse {
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
    private List<PostFileResponse> userPostFileList;
    private List<CommentsResponse> userPostCommentsList;
    private List<PostLikeResponse> postLikeList;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.tag = post.getTag();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.createdAt = post.getCreatedAt();
        this.userId = post.getUser().getId();
    }

    public void setDayBefore(String dayBefore) {
    }

    public void addTag(String tagName) {
    }


}