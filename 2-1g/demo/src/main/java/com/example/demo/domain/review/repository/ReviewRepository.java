package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Review 엔티티를 다루는 레포지토리, PK타입은 Long
// JpaRepository를 상속받으면 save, findById, findAll, delete, count 같은 기본 메서드를 자동으로 생성해줌
// 따라서 직접 구현 클래스를 만들 필요가 없음 
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStoreId(Long storeId, Pageable pageable);

    Page<Review> findAllByMemberId(Long memberId, Pageable pageable);
}
