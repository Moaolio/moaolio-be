package com.example.side.techStack.Dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechStackRequest {
    private Long id;
    private Long userId;
    private String techStack;
}