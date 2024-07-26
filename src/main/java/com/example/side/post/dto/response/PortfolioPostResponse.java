<<<<<<<< HEAD:src/main/java/com/example/side/user/scrap/Dto/response/UserScrapResponse.java
package com.example.side.user.scrap.Dto.response;
========
package com.example.side.post.dto.response;
>>>>>>>> feat/#4-JWT-OAuth2-Client:src/main/java/com/example/side/post/dto/response/PortfolioPostResponse.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioPostResponse {

    private Long id;
    private String title;
    private String content;

}
