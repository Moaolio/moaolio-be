package com.example.side.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostCommentsRequest {
    private Long id;
    private Long userPostId;
    private Long userId;
    private String description;
}