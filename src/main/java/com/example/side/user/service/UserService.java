package com.example.side.user.service;

import com.example.side.common.exception.UserNotFoundException;
import com.example.side.user.dto.request.UserPasswordFindRequest;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.request.UsernameFindRequest;
import com.example.side.user.dto.response.UserPasswordFindResponse;
import com.example.side.user.dto.response.UsernameFindResponse;
import com.example.side.user.dto.response.UserSignUpResponse;
import com.example.side.user.entity.User;
import com.example.side.user.entity.UserRole;
import com.example.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
                .birth(userSignUpRequest.getBirth())
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);

        UserSignUpResponse userSignUpResponse = UserSignUpResponse.builder()
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .birth(savedUser.getBirth())
                .build();
        return userSignUpResponse;
    }

    public UsernameFindResponse findUsername(UsernameFindRequest usernameFindRequest) throws IllegalStateException, UserNotFoundException {
        Optional<User> findUser = userRepository.findByEmail(usernameFindRequest.getEmail());
        if (findUser.isPresent()) {
            User user = findUser.get();
            return UsernameFindResponse.builder()
                    .username(user.getUsername())
                    .build();
        }
        else {
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }
    }

    public UserPasswordFindResponse findPassword(UserPasswordFindRequest userPasswordFindRequest) throws IllegalStateException {
        Optional<User> findUser = userRepository.findByUsernameAndEmail(userPasswordFindRequest.getUsername(), userPasswordFindRequest.getEmail());
        if (findUser.isPresent()) {
            User user = findUser.get();
            return UserPasswordFindResponse.builder()
                    .password(user.getPassword())
                    .build();
        }
        else {
            throw new IllegalStateException("비밀번호를 찾을 수 없습니다.");
        }
    }
}
