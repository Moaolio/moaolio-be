package com.example.side.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
@AllArgsConstructor
public class UsernameFindRequest {

    private String username;
    @JsonCreator
    public UsernameFindRequest(@JsonProperty("username") String username, @JsonProperty("email") String email) {
        this.username = username;
    }

}
