package com.example.side.repository;

import com.example.side.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByNickname(String nickname);
    User findByCareer(Long career);
    User findByState(String state);
    User findByAge(String age);
}