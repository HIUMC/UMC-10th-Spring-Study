package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 커서 기준 - 첫 페이지용 (커서 없음)
    Slice<Review> findByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    // ID 커서 기준 - 다음 페이지용
    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(
            Long memberId, Long idCursor, Pageable pageable
    );

    // 별점 커서 기준 - 첫 페이지용 (커서 없음)
    Slice<Review> findByMember_IdOrderByStarDescIdDesc(Long memberId, Pageable pageable);

    // 별점 커서 기준 - 다음 페이지용 (별점, ID 복합 커서)
    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId " +
            "AND ( r.star < :starCursor " +
            "   OR (r.star = :starCursor AND r.id < :idCursor) ) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("starCursor") BigDecimal starCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );
}
