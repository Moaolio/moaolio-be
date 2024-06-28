package com.example.side.controller;

import com.example.side.response.CommentsResponse;
import com.example.side.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentsController {

    private final CommentsService commentsService;

    // 생성
    @PostMapping("/post")
    public CommentsResponse createComments

    @PutMapping("/update/{commentsId}")
    public CommentsResponse updateComments

    @DeleteMapping("/delete/{commentsId}")
    public void deleteComments
}
