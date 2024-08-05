package com.example.side.user.service;

import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.response.UserSignUpResponse;
import com.example.side.user.entity.User;
import com.example.side.user.entity.UserRole;
import com.example.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) throws IllegalStateException {
        boolean existData = userRepository.existsByUsername(userSignUpRequest.getUsername());
        if (existData) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        User user = User.builder()
                .username(userSignUpRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(userSignUpRequest.getPassword())) // 암호화 필요
                .email(userSignUpRequest.getEmail())
                .name(userSignUpRequest.getName())
                .nickname(userSignUpRequest.getNickName())
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);

        UserSignUpResponse userSignUpResponse = UserSignUpResponse.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .nickName(savedUser.getNickname())
                .build();
        return userSignUpResponse;
    }
}
