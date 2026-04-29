package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.UserTerms;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsRepository extends JpaRepository<UserTerms, Long> {
}
