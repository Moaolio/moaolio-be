package com.example.side.user.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsernameExistResponse {

    private boolean isAvailable;


}
