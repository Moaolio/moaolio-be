package com.example.side.techStack.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechStackResponse {
    private Long id;

    private Long userId;
    private String techStack;
}