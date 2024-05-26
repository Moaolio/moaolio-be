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
public class UserResponse {
    private Long id;
    private String name;
    private String profile;
    private String Nickname;
    private String description;
    private String State;

    private String Kakao;
    private String Google;
    private String Naver;

    private LocalDateTime Created;
    private LocalDateTime Updated;
    private List<NotificationResponse> notificationList;
    private List<TechStackListResponse> techStackList;
    private List<UserPostCommentsResponse> userPostComments;
    private List<JobResponse> jobList;
    private List<UserPostResponse> userPostList;
    private List<PostLikeResponse> userPostLikeList;
    private List<UserPostCommentsResponse> userPostCommentsList;
    private List<UserScrapResponse> userScrapList;

}
