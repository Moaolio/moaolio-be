package com.example.side.user.service;

import com.example.side.common.exception.UserNotFoundException;
import com.example.side.techStack.entity.TechStack;
import com.example.side.techStack.repository.TechStackRepository;
import com.example.side.techStack.service.TechStackService;
import com.example.side.user.dto.request.UidFindRequest;
import com.example.side.user.dto.request.UserPasswordFindRequest;
import com.example.side.user.dto.request.UserSignUpRequest;
import com.example.side.user.dto.request.UserUpdateRequest;
import com.example.side.user.dto.response.*;
import com.example.side.user.entity.User;
import com.example.side.user.entity.UserRole;
import com.example.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TechStackService techStackService;
    private final TechStackRepository techStackRepository;

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

    public UidFindResponse findUid(UidFindRequest uidFindRequest) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(uidFindRequest.getEmail());
        if (user.isPresent()) {
            return UidFindResponse.builder()
                    .uid(user.get().getUid())
                    .build();
        }
        else {
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }
    }

    public UidExistResponse existUid(UidFindRequest usernameFindRequest) {
        Optional<User> findUser = userRepository.findByUid(usernameFindRequest.getEmail());
        if (findUser.isPresent()) {
            return new UidExistResponse(false);
        }
        else {
            return new UidExistResponse(true);
        }
    }

    public UserPasswordFindResponse findPassword(UserPasswordFindRequest userPasswordFindRequest) throws IllegalStateException {
        Optional<User> findUser = userRepository.findByUidAndEmail(userPasswordFindRequest.getUid(), userPasswordFindRequest.getEmail());
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

    @Transactional
    public UserUpdateResponse update(UserUpdateRequest userUpdateRequest) throws IllegalStateException {
        List<String> myStack = userUpdateRequest.getMyStack();
        List<TechStack> techStacks = myStack.stream()
                .map(stack -> techStackRepository.findByTechName(stack))  // 이름으로 개별 조회
                .filter(Objects::nonNull)  // null 값 제외 (해당 이름이 없을 경우)
                .collect(Collectors.toList());

        Optional<User> findUser = userRepository.findByUid(userUpdateRequest.getUid());
        if (findUser.isPresent()) {
            User user = findUser.get();
            user.updateUserInfo(
                    userUpdateRequest.getNickname(),
                    userUpdateRequest.getIntroduction(),
                    userUpdateRequest.getContactInformation(),
                    userUpdateRequest.getExperience(),
                    userUpdateRequest.getPhone(),
                    techStacks);

            List<String> stacks = user.toStacks(user.getTechStacks());

            UserUpdateResponse userUpdateResponse = new UserUpdateResponse(
                    user.getNickname(), user.getDescription(), stacks, user.getContactInformation(), user.getExperience(), user.getPhone());

            return userUpdateResponse;
        }
        else {
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }

    }

}
