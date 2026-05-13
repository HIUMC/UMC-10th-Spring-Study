package com.example.umc10th.domain.restaurant.repository;

import com.example.umc10th.domain.restaurant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
