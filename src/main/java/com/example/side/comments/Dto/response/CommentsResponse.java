package com.example.side.comments.dto.response;

import com.example.side.comments.entity.Comments;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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