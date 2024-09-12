package com.example.side.user.repository;

import com.example.side.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String uid);
    Optional<User> findByEmail(String email);
    Optional<User> findByUidAndEmail(String username, String email);

    /**
     * 이미 존재하는지 확인
     * 자체 회원가입 아이디 중복확인
     * @param uid
     * @return
     */
    boolean existsByUid(String uid);

    /**
     * 닉네임 중복확인
     * @param nickname
     * @return
     */
    boolean existsByNickname(String nickname);

    User findByVerificationToken(String token);

}
