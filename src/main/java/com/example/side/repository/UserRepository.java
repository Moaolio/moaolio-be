package com.example.side.repository;

import com.example.side.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByNickname(String nickname);
    User findByCareer(Long career);
    User findByState(String state);
    User findByAge(String age);

}