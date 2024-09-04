package com.example.side.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter @Setter
@NoArgsConstructor
public class UidFindRequest {

    private String email;

    @JsonCreator
    public UidFindRequest(@JsonProperty("email") String email) {
        this.email = email;
    }

}
