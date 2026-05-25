package com.example.umt10th.domain.member.repository;

import com.example.umt10th.domain.member.entity.Food;
import com.example.umt10th.domain.member.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByFoodType(FoodType type);
}
