package com.example.side.user.service;

import com.example.side.common.exception.UserNotFoundException;
import com.example.side.user.dto.request.UserPasswordFindRequest;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.request.UsernameFindRequest;
import com.example.side.user.dto.response.UserPasswordFindResponse;
import com.example.side.user.dto.response.UidExistResponse;
import com.example.side.user.dto.response.UidFindResponse;
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

        boolean existData = userRepository.existsByUid(userSignUpRequest.getUid());
        if (existData) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        User user = User.builder()
                .uid(userSignUpRequest.getUid())
                .password(bCryptPasswordEncoder.encode(userSignUpRequest.getPassword())) // 암호화 필요
                .email(userSignUpRequest.getEmail())
                .name(userSignUpRequest.getName())
                .birth(userSignUpRequest.getBirth())
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);

        UserSignUpResponse userSignUpResponse = UserSignUpResponse.builder()
                .uid(savedUser.getUid())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .birth(savedUser.getBirth())
                .build();
        return userSignUpResponse;
    }

    public UidFindResponse findUid(UsernameFindRequest usernameFindRequest) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUid(usernameFindRequest.getUid());
        if (user.isPresent()) {
            return UidFindResponse.builder()
                    .uid(usernameFindRequest.getUid())
                    .build();
        }
        else {
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }
    }

    public UidExistResponse existUid(UsernameFindRequest usernameFindRequest) {
        Optional<User> findUser = userRepository.findByUid(usernameFindRequest.getUid());
        if (findUser.isPresent()) {
            return new UidExistResponse(false);
        }
        else {
            return new UidExistResponse(true);
        }
    }

    public UserPasswordFindResponse findPassword(UserPasswordFindRequest userPasswordFindRequest) throws IllegalStateException {
        Optional<User> findUser = userRepository.findByUsernameAndEmail(userPasswordFindRequest.getUid(), userPasswordFindRequest.getEmail());
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
