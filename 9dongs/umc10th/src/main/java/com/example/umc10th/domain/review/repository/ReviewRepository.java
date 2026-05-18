package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant WHERE r.member.id = :memberId AND r.id < :reviewId ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdCursor(@Param("memberId") Long memberId, @Param("reviewId") Long reviewId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant WHERE r.member.id = :memberId AND (r.star < :star OR (r.star = :star AND r.id < :reviewId)) ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findMyReviewsByStarCursor(@Param("memberId") Long memberId, @Param("star") Float star, @Param("reviewId") Long reviewId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findMyReviewsByIdOrder(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant WHERE r.member.id = :memberId ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findMyReviewsByStarOrder(@Param("memberId") Long memberId, Pageable pageable);
}