package com.example.umc10th.domain.food.repository;
import com.example.umc10th.domain.food.entity.UserFood;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
    List<UserFood> findAllByUserId(Long userId);
}
