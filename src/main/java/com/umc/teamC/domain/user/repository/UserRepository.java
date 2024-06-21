package com.umc.teamC.domain.user.repository;

import com.umc.teamC.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    User findByUsername(String username);

    //Optional<User> findById(Long id);
}