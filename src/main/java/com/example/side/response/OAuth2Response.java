package com.example.side.response;

public interface OAuth2Response {

    String getProvider(); // 제공자 이름

    String getProviderId(); // 제공자가 부여하는 유저 구분 ID

    String getEmail();

    String getName();
}
