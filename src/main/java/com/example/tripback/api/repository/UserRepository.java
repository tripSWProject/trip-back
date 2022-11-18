package com.example.tripback.api.repository;

import com.example.tripback.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail);
    User findByUserId(String userId);
    Optional<User> findByUserSeq(Long userSeq);
}
