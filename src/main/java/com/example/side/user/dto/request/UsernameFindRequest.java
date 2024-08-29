package com.example.side.user.dto.request;

import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsernameFindRequest {

    private String username;

}
