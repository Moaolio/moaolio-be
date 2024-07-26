<<<<<<<< HEAD:src/main/java/com/example/side/notification/Dto/request/NotificationRequest.java
package com.example.side.notification.Dto.request;
========
package com.example.side.notification.dto.request;
>>>>>>>> feat/#4-JWT-OAuth2-Client:src/main/java/com/example/side/notification/dto/request/NotificationRequest.java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {
    private String title;
    private String discription;
    private Long userId;
}