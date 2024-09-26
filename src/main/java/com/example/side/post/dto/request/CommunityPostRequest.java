package com.example.side.post.dto.request;

import com.example.side.post.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityPostRequest {
    private String title;
    private String description;
    private Long categoryId;
}
