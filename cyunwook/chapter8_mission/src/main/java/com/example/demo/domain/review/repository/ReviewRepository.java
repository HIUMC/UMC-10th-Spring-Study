package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 멤버의 리뷰 목록 (마이페이지용)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId ORDER BY r.createdAt DESC")
    Page<Review> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    // 특정 멤버의 리뷰 개수 (마이페이지용)
    @Query("SELECT COUNT(r) FROM Review r WHERE r.member.id = :memberId")
    Long countByMemberId(@Param("memberId") Long memberId);

    //id순 조회
    // cursor보다 작은 ID, ID 내림차순
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.id DESC")
    List<Review> findByMemberIdOrderById(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable);
    //별점 순 조회
    // cursor보다 작은 ID, 별점 내림차순
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.id < :cursor ORDER BY r.star DESC, r.id DESC")
    List<Review> findByMemberIdOrderByStar(
            @Param("memberId") Long memberId,
            @Param("cursor") Long cursor,
            Pageable pageable);
}