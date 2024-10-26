package com.example.side.post.service;

import com.example.side.Exception.CustomException;
import com.example.side.auth.CustomUserDetails;
import com.example.side.comments.entity.Comments;
import com.example.side.post.category.Category;
import com.example.side.post.category.CategoryRepository;
import com.example.side.post.dto.response.PortfolioPostResponse;
import com.example.side.post.entity.CommunityPost;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.entity.PostType;
import com.example.side.post.like.entity.PostLike;
import com.example.side.post.like.repository.PostLikeRepository;
import com.example.side.post.repository.CommunityPostRepository;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import com.example.side.post.dto.request.CommunityPostRequest;
import com.example.side.post.dto.response.CommunityPostResponse;
import com.example.side.comments.dto.response.CommentsResponse;
import com.oracle.bmc.objectstorage.ObjectStorage;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static com.example.side.Exception.ErrorCode.NOT_FOUND_POST;

@RequiredArgsConstructor
@Service
public class CommunityPostService {
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;
    private final PostLikeRepository postLikeRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectStorage objectStorageClient;

    static final String NAMESPACE = "cnshljz7n2kf";
    static final String BUCKET_NAME = "bucket-20240928-2302";

    private static final String REGION = "ap-sydney-1";

    public class CommonUtils {

        public static String buildFileName(String originalFilename) {
            // 파일 확장자 추출
            String extension = "";
            int i = originalFilename.lastIndexOf('.');
            if (i > 0) {
                extension = originalFilename.substring(i);
            }

            // UUID를 사용하여 고유한 파일 이름 생성
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            return uniqueFileName;
        }
    }
    @Transactional //생성
    public CommunityPostResponse createPost(CommunityPostRequest communityPostRequest, CustomUserDetails userDetails, MultipartFile multipartFile) {
        String imgUrl = null;
        if(multipartFile != null && !multipartFile.isEmpty()){
            String fileName = CommunityPostService.CommonUtils.buildFileName(multipartFile.getOriginalFilename());
            try{
                String objectName = UUID.randomUUID().toString()+"_"+fileName;
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .namespaceName(NAMESPACE)
                        .bucketName(BUCKET_NAME)
                        .objectName(objectName)
                        .putObjectBody(multipartFile.getInputStream())
                        .contentType(multipartFile.getContentType())
                        .contentLength(multipartFile.getSize())
                        .build();
                objectStorageClient.putObject(putObjectRequest);
                imgUrl = "https://objectstorage." + REGION + ".oraclecloud.com/n/" + NAMESPACE + "/b/" + BUCKET_NAME + "/o/" + objectName;
            } catch (Exception e) {
                return CommunityPostResponse.fail("UPLOAD_FAILED", "파일 업로드에 실패했습니다: " + e.getMessage());
            }
        }

        Category category = categoryRepository.findById(communityPostRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        CommunityPost post = CommunityPost.builder()
                .category(category)
                .imgUrl(imgUrl)
                .build();
        post.setUser(userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        post.setPostType(PostType.COMMUNITY);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setTitle(communityPostRequest.getTitle());
        post.setDescription(communityPostRequest.getDescription());
        post.setLikeCount(0L);
        post.setViewCount(0L);
        communityPostRepository.save(post);

        return CommunityPostResponse.from(post);
    }

    @Transactional//수정
    public CommunityPostResponse updatePost(Long postId, CommunityPostRequest postRequest, CustomUserDetails userDetails) {
        CommunityPost communityPost = communityPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!communityPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }

        Category category = categoryRepository.findById(postRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        communityPost.update(postRequest.getTitle(), postRequest.getDescription(), category);

        return new CommunityPostResponse(communityPostRepository.save(communityPost));
    }

    @Transactional
    public HashMap<String, Long> deletePost(Long postId, CustomUserDetails userDetails) {
        CommunityPost post = communityPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new AccessDeniedException("You can only delete your own posts");
        }

        communityPostRepository.deleteById(postId);

        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", post.getId());
        return responseId;
    }
    //조회
    @Transactional(readOnly = true)
    public Page<CommunityPostResponse> communityPosts(Pageable pageable){
        Page<CommunityPost> posts = communityPostRepository.findAll(pageable);
        return posts.map(CommunityPostResponse::new);
    }
    //상세조회
    @Transactional
    public CommunityPostResponse getPostId(Long postId, User user){
        CommunityPost communityPost = communityPostRepository.findById(postId).orElseThrow(()->new CustomException(NOT_FOUND_POST));
        communityPost.getComments().sort(Comparator.comparing(Comments::getCreatedAt).reversed());
        List<CommentsResponse> commentsResponses = new ArrayList<>();
        for(Comments comments : communityPost.getComments()){
            commentsResponses.add(new CommentsResponse(comments));
        }
        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId,user.getId());

        if(postLike != null){
            return CommunityPostResponse.of(communityPost,commentsResponses,true);
        }
        else{
            return CommunityPostResponse.of(communityPost,commentsResponses,false);
        }
    }
    //검색
    @Transactional(readOnly = true)
    public List<CommunityPostResponse> searchPostsByTitle(String title) {
        List<CommunityPost> posts = communityPostRepository.findPostsByTitle(title);
        return posts.stream()
                .map(CommunityPostResponse::new)
                .collect(Collectors.toList());
    }
    //카테고리 검색
    @Transactional(readOnly = true)
    public List<CommunityPostResponse> findByCategoryId(Long categoryId) {
        List<CommunityPost> posts = communityPostRepository.findByCategoryId(categoryId);
        return posts.stream()
                .map(CommunityPostResponse::new)
                .collect(Collectors.toList());
    }
    //내 게시글 조회
    @Transactional(readOnly = true)
    public Page<CommunityPostResponse> findMyPosts(Pageable pageable, CustomUserDetails userDetails) {
        User user = Optional.ofNullable(userDetails.getUser())
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
        Page<CommunityPost> postPage = communityPostRepository.findMyPosts(user,pageable);
        return postPage.map(CommunityPostResponse::new);
    }
}
