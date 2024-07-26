package com.example.side.comment.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsRequest {

    private Long userPostId;
    private Long userId;
    private String description;

}