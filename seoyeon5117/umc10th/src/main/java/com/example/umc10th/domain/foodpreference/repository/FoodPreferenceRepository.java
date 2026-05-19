package com.example.umc10th.domain.foodpreference.repository;

import com.example.umc10th.domain.foodpreference.entity.FoodPreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodPreferenceRepository extends JpaRepository<FoodPreference, Long> {
}
