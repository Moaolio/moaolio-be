package com.example.side.post.Dto.request;

import lombok.Data;

@Data
public class PostFilterRequest {
    private String tag;
    private Long maxViews;

}
