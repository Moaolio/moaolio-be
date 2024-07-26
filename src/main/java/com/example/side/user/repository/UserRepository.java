package com.example.side.user.repository;

import com.example.side.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    User findById(long id);
    Optional<User> findByUsername(String username);
    User findByGoogleId(String googleId);
    User findByKakaoId(String kakaoId);
    User findByNaverId(String naverId);


}