package com.example.umc10th.domain.user.repository;

import java.util.List;
import java.util.Optional;

import com.example.umc10th.domain.common.enums.SocialType;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findBySocialTypeAndSocialUid(SocialType socialType, String socialUid);

}
