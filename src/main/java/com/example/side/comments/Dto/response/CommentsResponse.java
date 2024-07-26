<<<<<<<< HEAD:src/main/java/com/example/side/comments/Dto/response/CommentsResponse.java
package com.example.side.comments.Dto.response;

import com.example.side.comments.entity.Comments;
import lombok.*;

@Getter
========
package com.example.side.comment.dto.response;

import com.example.side.comment.entity.Comments;
import lombok.*;

@Data
>>>>>>>> feat/#4-JWT-OAuth2-Client:src/main/java/com/example/side/comment/dto/response/CommentsResponse.java
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentsResponse {

    private Long id;
    private String description;
    private Long userId;
    private Long postId;

    public CommentsResponse(Comments comments) {
        this.id = comments.getId();
        this.description = comments.getDescription();
        this.userId = comments.getUser().getId();
        this.postId = comments.getPost().getId();

    }
}