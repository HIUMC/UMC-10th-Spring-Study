package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
