package com.example.side.user.repository;

import com.example.side.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndEmail(String username, String email);

    /**
     * 이미 존재하는지 확인
     * 자체 회원가입 아이디 중복확인
     * @param username
     * @return
     */
    boolean existsByUsername(String username);

    /**
     * 닉네임 중복확인
     * @param nickname
     * @return
     */
    boolean existsByNickname(String nickname);

    User findByVerificationToken(String token);

}
