package com.example.side.response;

import com.example.side.model.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsResponse {
    private Long id;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;
    private Long postId;

    public CommentsResponse(Comments comments) {
        this.id = comments.getId();
        this.description = comments.getDescription();
        this.createdAt = comments.getCreatedAt();
        this.updatedAt = comments.getUpdatedAt();
        this.userId = comments.getUser().getId();
        this.postId = comments.getPost().getId();
    }
}