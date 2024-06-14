package com.example.side.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityRequest {
    private Long id;
    private String occupation;
}