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
public class CommunityResponse {
    private Long id;
    private String occupation;
    private List<UserPostResponse> userPostList;
}