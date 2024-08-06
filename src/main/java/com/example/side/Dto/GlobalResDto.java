package com.example.side.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResDto<T> {
    private int status;
    private T data;
    private T msg;



    public static <T> GlobalResDto<Object> success(T data, String msg) {
        return new GlobalResDto<>(200, data, msg);
    }

    public static <T> GlobalResDto<Object> fail(int status, T data, String msg) {
        return new GlobalResDto<>(status, data, msg);
    }
    public static GlobalResDto<String> fail(String msg) {
        return new GlobalResDto<>(400, null, msg);
    }

}
