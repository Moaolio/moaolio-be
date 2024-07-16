package com.example.side.response;

import com.example.side.model.entity.Comments;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentsResponse {
    private Long id;
    private String description;

    private Long userId;
    private Long postId;

    public CommentsResponse(Comments comments) {
        this.id = comments.getId();
        this.description = comments.getDescription();
        this.userId = comments.getUser().getId();
        this.postId = comments.getPost().getId();
    }
}