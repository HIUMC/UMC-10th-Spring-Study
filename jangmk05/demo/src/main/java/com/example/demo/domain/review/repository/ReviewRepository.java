package com.example.demo.domain.review.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByMemberAndMission(Member member, Mission mission);
    Slice<Review> findByStore_IdOrderByIdDesc(Long storeId, Pageable pageable);

    Slice<Review> findByStore_IdAndIdLessThanOrderByIdDesc(Long storeId, Long idCursor, Pageable pageable);
}
