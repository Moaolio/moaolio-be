package com.example.side.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
public class UsernameFindRequest {

    private String uid;

    @JsonCreator
    public UsernameFindRequest(@JsonProperty("uid") String uid) {
        this.uid = uid;
    }

}
