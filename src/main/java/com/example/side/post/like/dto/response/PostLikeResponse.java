package com.example.side.post.like.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponse {
    private Long id;
    private Long userId;
    private Long userPostId;
}