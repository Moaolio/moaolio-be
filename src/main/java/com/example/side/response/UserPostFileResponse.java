package com.example.side.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPostFileResponse {
    private Long id;
    private String title;
    private String description;
    private String fileUrl;
    private String fileType;
    private Long fileSize;

    private Long userPostId;
}