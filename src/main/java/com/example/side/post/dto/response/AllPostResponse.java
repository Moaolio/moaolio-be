<<<<<<<< HEAD:src/main/java/com/example/side/post/Dto/response/AllPostResponse.java
package com.example.side.post.Dto.response;
========
package com.example.side.post.dto.response;
>>>>>>>> feat/#4-JWT-OAuth2-Client:src/main/java/com/example/side/post/dto/response/AllPostResponse.java

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AllPostResponse {
    private List<PostResponse> posts;
}
