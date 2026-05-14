package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 순 커서 기반 페이지네이션
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.id DESC")
    Slice<Review> findByMemberIdOrderById(@Param("memberId") Long memberId, @Param("cursor") Long cursor, Pageable pageable);

    // 커서 없을 때 ID 순
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.id DESC")
    Slice<Review> findByMemberIdOrderById(@Param("memberId") Long memberId, Pageable pageable);

    // 별점 순 커서 기반 페이지네이션
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND (r.star < :star OR (r.star = :star AND r.id < :cursor)) ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByStar(@Param("memberId") Long memberId, @Param("star") java.math.BigDecimal star, @Param("cursor") Long cursor, Pageable pageable);

    // 커서 없을 때 별점 순
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findByMemberIdOrderByStar(@Param("memberId") Long memberId, Pageable pageable);
}