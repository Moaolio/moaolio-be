package com.example.side.Dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2UserDto implements OAuth2User {

    private final UserDto userDto;

    public CustomOAuth2UserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userDto.getRole();
            }
        });
        return collection;
    }

    public String getName() {
        return userDto.getNickname();
    }

    public String getUserName() {
        return userDto.getUsername();
    }

}
