package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.mapping.FoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodPreferenceRepository extends JpaRepository<FoodPreference, Long> {
}
