package com.example.side.controller;

import com.example.side.model.entity.UserPost;
import com.example.side.request.UserPostRequest;
import com.example.side.response.UserPostResponse;
import com.example.side.service.UserPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class UserPostController {
    private final UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @PostMapping("/create")
    public UserPostResponse createPost(@RequestBody UserPostRequest userPostRequest) {
        return userPostService.createPost(userPostRequest);
    }
    @PutMapping("/update/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody UserPostRequest userPostRequest) {
        return userPostService.updatePost(id, userPostRequest);

    }
    @DeleteMapping("/delete/{id}")
    public Long deletePost(@PathVariable Long id) {
        return userPostService.deletePost(id);
    }
    @GetMapping("/get/user/{userId}")
public Iterable<UserPostResponse> getPostsByUser(@PathVariable Long userId) {
    Iterable<UserPost> userPosts = userPostService.selectByUserId(userId);
    List<UserPostResponse> userPostResponses = new ArrayList<>();
    for (UserPost userPost : userPosts) {
        UserPostResponse userPostResponse = new UserPostResponse();
        // UserPost 객체의 필드를 UserPostResponse 객체에 복사합니다.
        userPostResponse.setTitle(userPost.getTitle());
        userPostResponse.setContent(userPost.getContent());
        // ... 다른 필드도 복사 ...
        userPostResponses.add(userPostResponse);
    }
    return userPostResponses;
}
    @GetMapping("/get")
    public Iterable<UserPostResponse> getAllPosts() {
        Iterable<UserPost> userPosts = userPostService.selectAll();
        List<UserPostResponse> userPostResponses = new ArrayList<>();
        for (UserPost userPost : userPosts) {
            UserPostResponse userPostResponse = new UserPostResponse();
            // UserPost 객체의 필드를 UserPostResponse 객체에 복사합니다.
            userPostResponse.setTitle(userPost.getTitle());
            userPostResponse.setContent(userPost.getContent());
            // ... 다른 필드도 복사 ...
            userPostResponses.add(userPostResponse);
        }
        return userPostResponses;
    }

//    @GetMapping("/search")
//    public Iterable<UserPostResponse> searchPosts(@RequestParam(required = false) String tag,
//                                                  @RequestParam(required = false) String category,
//                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime createdAt) {
//        Iterable<UserPost> userPosts = userPostService.searchPosts( category, tag, createdAt);
//        List<UserPostResponse> userPostResponses = new ArrayList<>();
//        for (UserPost userPost : userPosts) {
//            UserPostResponse userPostResponse = new UserPostResponse();
//            // UserPost 객체의 필드를 UserPostResponse 객체에 복사합니다.
//            userPostResponse.setTitle(userPost.getTitle());
//            userPostResponse.setContent(userPost.getContent());
//            // ... 다른 필드도 복사 ...
//            userPostResponses.add(userPostResponse);
//        }
//        return userPostResponses;
//    }

}