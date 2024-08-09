package com.example.side.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
public class DefaultApiResponse<T> {

    private int statusCode;
    private String responseMessage;
    private T data;

    public DefaultApiResponse(int statusCode, String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static <T> DefaultApiResponse<T> success(T data) {
        return (DefaultApiResponse<T>) DefaultApiResponse.builder()
                .statusCode(StatusCode.OK)
                .responseMessage(ResponseMessage.CREATED_USER)
                .data(data)
                .build();
    }

}
