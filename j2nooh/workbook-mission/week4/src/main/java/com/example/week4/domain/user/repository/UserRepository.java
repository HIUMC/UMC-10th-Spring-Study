package com.example.week4.domain.user.repository;

import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findBySocialProviderAndSocialLoginId (
            SocialProvider socialProvider, String socialLoginId
    );
}
