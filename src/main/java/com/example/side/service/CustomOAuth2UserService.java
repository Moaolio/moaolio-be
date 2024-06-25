package com.example.side.service;

import com.example.side.Dto.CustomOAuth2UserDto;
import com.example.side.Dto.UserDto;
import com.example.side.model.entity.User;
import com.example.side.repository.UserRepository;
import com.example.side.response.GoogleResponse;
import com.example.side.response.NaverResponse;
import com.example.side.response.OAuth2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService { // OAuth2UserServicie의 구현체인 DefaultOAuth2UserService

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException { // 소셜 사용자 정보 데이터를 인자로 받아옴
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());

        /*
        정보 데이터를 제공하는 소셜 포탈이 누군지 구분해야함
        인증 데이터 규격이 서로 조금씩 다르기 때문에 인터페이스를 선언 후 DTO를 따로 만들어야함
         */
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")) {

        }
        else if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        // 리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디 값을 만듬
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        User existData = userRepository.findByUsername(username);

        if (existData == null) {
            User user = User.builder()
                    .nickname(oAuth2Response.getName())
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .role("ROLE_USER")
                    .build();

            userRepository.save(user);

            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setNickname(oAuth2Response.getName());
            userDto.setRole("ROLE_USER");

            return new CustomOAuth2UserDto(userDto);
        }
        else {
            existData.setEmail(oAuth2Response.getEmail());
            existData.setNickname(oAuth2Response.getName());

            userRepository.save(existData);

            UserDto userDto = new UserDto();
            userDto.setUsername(existData.getUsername());
            userDto.setNickname(oAuth2Response.getName());
            userDto.setRole(existData.getRole());

            return new CustomOAuth2UserDto(userDto);
        }

    }

}
