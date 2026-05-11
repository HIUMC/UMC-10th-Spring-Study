package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 커서
    Page<Review> findByMemberIdAndIdGreaterThan(Long memberId, Long cursor, Pageable pageable);

    // 별점 순 커서
    Page<Review> findByMemberIdAndRateLessThan(Long memberId, Float cursor, Pageable pageable);

    // 첫 페이지 (cursor null일 때)
    Page<Review> findByMemberId(Long memberId, Pageable pageable);
}

