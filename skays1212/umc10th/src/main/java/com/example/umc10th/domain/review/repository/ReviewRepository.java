package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findByMember_IdOrderByStarDescIdDesc(Long memberId, Pageable pageable);

    @Query("""
            select r
            from Review r
            where r.member.id = :memberId
              and (r.star < :star or (r.star = :star and r.id < :cursorId))
            order by r.star desc, r.id desc
            """)
    Slice<Review> findByMemberIdCursorByStar(
            @Param("memberId") Long memberId,
            @Param("star") BigDecimal star,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    Slice<Review> findByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    Slice<Review> findByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, Long cursorId, Pageable pageable);
}
