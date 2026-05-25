package com.example.umt10th.domain.member.repository;

import com.example.umt10th.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
