package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Food;
import com.example.demo.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
