package com.example.side.repository;

import com.example.side.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    User findById(long id);
    User findByUsername(String username);
    User findByGoogleId(String googleId);
    User findByKakaoId(String kakaoId);
    User findByNaverId(String naverId);
}