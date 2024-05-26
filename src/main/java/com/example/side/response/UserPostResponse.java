package com.example.side.response;

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

}