package com.example.side.post.Dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AllPostResponse {
    private List<PostResponse> posts;
}
