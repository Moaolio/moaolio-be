package com.example.side.post.service;

import com.example.side.Exception.CustomException;
import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.comments.entity.Comments;
import com.example.side.config.UserDetailsImpl;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.like.entity.PostLike;
import com.example.side.post.like.repository.PostLikeRepository;
import com.example.side.post.repository.PortfolioPostRepository;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.PortfolioPostResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.side.Exception.ErrorCode.NOT_FOUND_POST;

@Service
@RequiredArgsConstructor
public class PortfolioPostService {
    private final PortfolioPostRepository portfolioPostRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;


    // 생성
    @Transactional
    public PortfolioPostResponse createPost(PortfolioPostRequest portfolioPostRequest, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = new PortfolioPost(portfolioPostRequest, userDetails.getUser());
        PortfolioPost savedPortfolioPost = portfolioPostRepository.save(portfolioPost);
       // savedPortfolioPost.setRepresentativeImageUrlAutomatically(url);
        return new PortfolioPostResponse(savedPortfolioPost);
    }

    // 수정
    @Transactional
    public PortfolioPostResponse updatePost(Long postId, PortfolioPostRequest postRequest, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!portfolioPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }

        portfolioPost.update(postRequest);
        PortfolioPost updatedPortfolioPost = portfolioPostRepository.save(portfolioPost);
        return new PortfolioPostResponse(updatedPortfolioPost);
    }

    // 삭제
    @Transactional
    public HashMap<String, Long> deletePost(Long postId, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!portfolioPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }

        portfolioPostRepository.deleteById(postId);
        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", portfolioPost.getId());
        return responseId;
    }
    // 전체조회
    public List<PortfolioPostResponse> portfolioPosts() {
        return portfolioPostRepository.findAll().stream()
                .map(PortfolioPostResponse::new)
                .collect(Collectors.toList());
    }
    //상세조회
    public PortfolioPostResponse getPostId(Long postId , User user){
        PortfolioPost portfolioPost = portfolioPostRepository.findById(postId).orElseThrow(()->new CustomException(NOT_FOUND_POST));
        portfolioPost.getComments().sort(Comparator.comparing(Comments::getCreatedAt).reversed());
        List<CommentsResponse> commentsResponses = new ArrayList<>();
        for(Comments c : portfolioPost.getComments()){
            commentsResponses.add(new CommentsResponse(c));
        }
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId,user.getId());
        if(postLike == null){
            return PortfolioPostResponse.of(portfolioPost,commentsResponses,false);
        }
        else{
            return PortfolioPostResponse.of(portfolioPost,commentsResponses,true);
        }
    }
    //필터링조회
    public List<PortfolioPost> getFilteredPosts(List<String> tags, LocalDateTime startDate, LocalDateTime endDate, String sortBy) {
        return portfolioPostRepository.findPosts(tags, startDate, endDate, sortBy);
    }
}