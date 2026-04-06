package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByStoreId(Long storeId);
}
