<<<<<<<< HEAD:src/main/java/com/example/side/notification/Dto/response/NotificationResponse.java
package com.example.side.notification.Dto.response;
========
package com.example.side.notification.dto.response;
>>>>>>>> feat/#4-JWT-OAuth2-Client:src/main/java/com/example/side/notification/dto/response/NotificationResponse.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;
}