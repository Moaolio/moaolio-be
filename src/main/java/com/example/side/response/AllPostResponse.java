package com.example.side.response;

import com.example.side.model.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AllPostResponse {
    private List<PostResponse> posts;
}
