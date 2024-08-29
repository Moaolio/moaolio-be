package com.example.side.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
public class UsernameFindRequest {

    private String username;

    @JsonCreator
    public UsernameFindRequest(@JsonProperty("username") String username) {
        this.username = username;
    }

}
