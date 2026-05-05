package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 멤버의 리뷰 목록 (마이페이지용)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    Page<Review> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    // 특정 멤버의 리뷰 개수 (마이페이지용)
    @Query("SELECT COUNT(r) FROM Review r WHERE r.member.id = :memberId")
    Long countByMemberId(@Param("memberId") Long memberId);
}