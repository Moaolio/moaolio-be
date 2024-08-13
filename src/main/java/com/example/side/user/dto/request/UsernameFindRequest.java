package com.example.side.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UsernameFindRequest {

    private String email;

}
