package com.example.side.post.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.auth.CustomUserDetails;
import com.example.side.post.dto.request.CommunityPostRequest;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.CommunityPostResponse;
import com.example.side.post.dto.response.PortfolioPostResponse;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.service.CommunityPostService;
import com.example.side.post.service.PortfolioPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PortfolioPostService portfolioPostService;
    private final CommunityPostService communityPostService;


    public PostController(PortfolioPostService portfolioPostService, CommunityPostService communityPostService) {
        this.portfolioPostService = portfolioPostService;
        this.communityPostService = communityPostService;
    }

    // 포트폴리오 게시글 생성
    @PostMapping("/create/portfolio")
    public GlobalResDto<PortfolioPostResponse> createPortfolioPost(@RequestPart MultipartFile multipartFile,
                                                                   @RequestBody PortfolioPostRequest portfolioPostRequest,
                                                                   @AuthenticationPrincipal CustomUserDetails userDetails) throws Exception {
        PortfolioPostResponse response = portfolioPostService.createPost(portfolioPostRequest, userDetails, multipartFile);
        return GlobalResDto.success(response, "게시글이 생성되었습니다.");
    }

    // 커뮤니티 게시글 생성
    @PostMapping("/create/community")
    public GlobalResDto<CommunityPostResponse> createCommunityPost(@RequestPart MultipartFile multipartFile,
                                                                   @RequestBody CommunityPostRequest communityPostRequest,
                                                                   @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommunityPostResponse response = communityPostService.createPost(communityPostRequest, userDetails,multipartFile);
        return GlobalResDto.success(response, "게시글이 성공적으로 생성되었습니다.");
    }

    // 포트폴리오 게시글 수정
    @PutMapping("/update/portfolio/{id}")
    public GlobalResDto<PortfolioPostResponse> updatePortfolioPost(@PathVariable("id") Long postId, @RequestBody PortfolioPostRequest portfolioPostRequest, @AuthenticationPrincipal CustomUserDetails userDetails) throws Exception {
        PortfolioPostResponse response = portfolioPostService.updatePost(postId, portfolioPostRequest, userDetails);
        return GlobalResDto.success(response, "게시글이 성공적으로 수정되었습니다.");
    }

    // 커뮤니티 게시글 수정
    @PutMapping("/update/community/{id}")
    public GlobalResDto<CommunityPostResponse> updateCommunityPost(@PathVariable("id") Long postId, @RequestBody CommunityPostRequest communityPostRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommunityPostResponse response = communityPostService.updatePost(postId, communityPostRequest, userDetails);
        return GlobalResDto.success(response, "커뮤니티 게시글이 성공적으로 수정되었습니다.");
    }

    // 포트폴리오 게시글 삭제
    @DeleteMapping("/delete/portfolio/{id}")
    public GlobalResDto<Long> deletePortfolioPost(@PathVariable("id") Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        portfolioPostService.deletePost(postId, userDetails);
        return GlobalResDto.success(postId, "포트폴리오 게시글이 성공적으로 삭제되었습니다.");
    }

    // 커뮤니티 게시글 삭제
    @DeleteMapping("/delete/community/{id}")
    public GlobalResDto<Long> deleteCommunityPost(@PathVariable("id") Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        communityPostService.deletePost(postId, userDetails);
        return GlobalResDto.success(postId, "커뮤니티 게시글이 성공적으로 삭제되었습니다.");
    }

    // 포트폴리오 게시글 조회 (최신순)
    @GetMapping("/get/portfolio")
    public GlobalResDto<Page<PortfolioPostResponse>> recentPortfolioPosts(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PortfolioPostResponse> response = portfolioPostService.portfolioPosts(pageable);
        return GlobalResDto.success(response, "포트폴리오 게시글 목록 조회가 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 조회 (최신순)
    @GetMapping("/get/community")
    public GlobalResDto<Page<CommunityPostResponse>> recentCommunityPosts(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommunityPostResponse> response = communityPostService.communityPosts(pageable);
        return GlobalResDto.success(response, "커뮤니티 게시글 목록 조회가 성공적으로 완료되었습니다.");
    }

    // 포트폴리오 게시글 상세 조회
    @GetMapping("/get/portfolio/{id}")
    public GlobalResDto<PortfolioPostResponse> getPortfolioPost(@PathVariable("id") Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        PortfolioPostResponse response = portfolioPostService.getPostId(postId, userDetails.getUser());
        return GlobalResDto.success(response, "포트폴리오 게시글 조회가 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 상세 조회
    @GetMapping("/get/community/{id}")
    public GlobalResDto<CommunityPostResponse> getCommunityPost(@PathVariable("id") Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommunityPostResponse response = communityPostService.getPostId(postId, userDetails.getUser());
        return GlobalResDto.success(response, "커뮤니티 게시글 조회가 성공적으로 완료되었습니다.");
    }

    // 포트폴리오 게시글 좋아요순 정렬
    @GetMapping("/get/portfolio/like")
    public GlobalResDto<List<PortfolioPostResponse>> likedPortfolioPosts() {
        List<PortfolioPostResponse> response = portfolioPostService.likedPosts();
        return GlobalResDto.success(response, "포트폴리오 게시글 좋아요순 정렬이 성공적으로 완료되었습니다.");
    }


    // 커뮤니티 게시글 검색
    @GetMapping("/community/search")
    public GlobalResDto<List<CommunityPostResponse>> searchCommunityPostsByTitle(@RequestParam(required = false) String title) {
        List<CommunityPostResponse> response = communityPostService.searchPostsByTitle(title);
        return GlobalResDto.success(response, "커뮤니티 게시글 검색이 성공적으로 완료되었습니다.");
    }

//     포트폴리오 게시글 검색
    @GetMapping("/portfolio/search")
    public GlobalResDto<List<PortfolioPost>> searchPortfolioPostsByTitle(@RequestParam(required = false) String keyword) {
        List<PortfolioPost> response = portfolioPostService.searchPostsByTitle(keyword);
        return GlobalResDto.success(response, "포트폴리오 게시글 검색이 성공적으로 완료되었습니다.");
    }

    // 커뮤니티 게시글 카테고리별 조회
    @GetMapping("/community/category")
    public GlobalResDto<List<CommunityPostResponse>> findCommunityPostsByCategory(@RequestParam(required = false) String category) {
        try {
            Long categoryId = Long.parseLong(category);
            List<CommunityPostResponse> response = communityPostService.findByCategoryId(categoryId);
            return GlobalResDto.success(response, "카테고리별 커뮤니티 게시글 조회가 성공적으로 완료되었습니다.");
        } catch (NumberFormatException e) {
            return GlobalResDto.error("Invalid category ID format.");
        }
    }
    //내 포트폴리오 게시글 조회
    @GetMapping("/my/portfolio")
    public GlobalResDto<Page<PortfolioPostResponse>> myPosts(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<PortfolioPostResponse> response = portfolioPostService.myPosts(pageable, userDetails);
        return GlobalResDto.success(response, "내 게시글 조회가 성공적으로 완료되었습니다.");
    }

    //내 커뮤니티 게시글 조회
    @GetMapping("/my/community")
    public GlobalResDto<Page<CommunityPostResponse>> myCommunityPosts(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CommunityPostResponse> response = communityPostService.findMyPosts(pageable, userDetails);
        return GlobalResDto.success(response, "내 게시글 조회가 성공적으로 완료되었습니다.");
    }

//스크럅운 추후에


//    //커뮤니티 게시글 스크랩
//    @PostMapping("/community/scrap/{postId}")
//    public HashMap<String, Long> scrapCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return communityPostService.scrapPost(postId, userDetails);
//    }
//    //커뮤니티 게시글 스크랩 취소
//    @DeleteMapping("/community/scrap/{postId}")
//    public HashMap<String, Long> unscrapCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return communityPostService.unscrapPost(postId, userDetails);
//    }
//    //커뮤니티 게시글 스크랩 조회
//    @GetMapping("/community/scrap")
//    public List<CommunityPostResponse> scrapedCommunityPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return communityPostService.scrapedPosts(userDetails);
//    }
//
//
//
//    //포트폴리오 게시물 스크랩
//    @PostMapping("/portfolio/scrap/{postId}")
//    public HashMap<String, Long> scrapPortfolioPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return portfolioPostService.scrapPost(postId, userDetails);
//    }
//    //포트폴리오 게시물 스크랩 취소
//    @DeleteMapping("/portfolio/scrap/{postId}")
//    public HashMap<String, Long> unscrapPortfolioPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return portfolioPostService.unscrapPost(postId, userDetails);
//    }
//    //포트폴리오 게시물 스크랩 조회
//    @GetMapping("/portfolio/scrap")
//    public List<PortfolioPostResponse> scrapedPortfolioPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return portfolioPostService.scrapedPosts(userDetails);
//    }
//


}
