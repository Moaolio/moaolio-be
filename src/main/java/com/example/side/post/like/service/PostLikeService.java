package com.example.side.post.like.service;

import com.example.side.Dto.GlobalResDto;
import com.example.side.Exception.CustomException;
import com.example.side.Exception.ErrorCode;
import com.example.side.auth.CustomUserDetails;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.entity.Post;
import com.example.side.post.like.entity.PostLike;
import com.example.side.post.like.repository.PostLikeRepository;
import com.example.side.post.repository.PortfolioPostRepository;
import com.example.side.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PortfolioPostRepository portfolioPostRepository;

    @Transactional
    public GlobalResDto<?> createPostLike(CustomUserDetails userDetails, Long postId) {
        User user = userDetails.getUser();
        Post post = isPostExist(postId);
        if(post != null){
            throw new CustomException(ErrorCode.NOT_FOUND_POST);
        }
        PostLike postLike1 = new PostLike(user, post);
        if(postLike1!=null){
            throw new CustomException(ErrorCode.DUPLICATED_POSTLIEK);
        }
        PostLike postLike = new PostLike(user, post);
        postLikeRepository.save(postLike);
        return GlobalResDto.success(null, "게시물 좋아요 성공");
    }
    @Transactional
    public GlobalResDto<?> deletePostLike(CustomUserDetails userDetails, Long postId) {
        User user = userDetails.getUser();
        Post post = isPostExist(postId);
        if(post == null){
            throw new CustomException(ErrorCode.NOT_FOUND_POST);
        }
        PostLike postLike = isPostLikeExist(user, post);
        if(postLike == null){
            throw new CustomException(ErrorCode.NOT_FOUND_POSTLIKE);
        }
        postLikeRepository.delete(postLike);
        return GlobalResDto.success(null, "게시물 좋아요 취소 성공");
    }
    public Post isPostExist(Long postId) {
        Optional<PortfolioPost> post = portfolioPostRepository.findById(postId);
        return post.orElse(null);
    }
    public PostLike isPostLikeExist(User user, Post post) {
        Optional<PostLike> postLike = postLikeRepository.findByUserAndPost(user, post);
        return postLike.orElse(null);

    }


    public Optional<PostLike> getPostLike(User user, Post post) {
        return postLikeRepository.findByUserAndPost(user, post);
    }
}