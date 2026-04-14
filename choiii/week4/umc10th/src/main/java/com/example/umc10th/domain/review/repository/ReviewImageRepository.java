package com.example.umc10th.User.review.repository;

import com.example.umc10th.domain.review.entity.ReviewImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findAllByReviewId(Long reviewId);
}
