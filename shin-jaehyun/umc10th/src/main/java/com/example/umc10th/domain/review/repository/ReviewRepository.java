package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = """
        SELECT DISTINCT r.*
        FROM review r
        LEFT JOIN review_image ri
        ON r.id = ri.review_id
        WHERE r.member_id = :memberId
        AND r.id < :idCursor
    """,nativeQuery = true)
    Slice<Review> getMyReviews_IdAndIdLessThanOrderByIdDesc(@Param("memberId") Long memberId, long idCursor, Pageable pageable);

    @Query(value = """
        SELECT DISTINCT r.*
        FROM review r
        LEFT JOIN review_image ri
        ON r.id = ri.review_id
        WHERE r.member_id = :memberId
    """,nativeQuery = true)
    Slice<Review> getMyReviews_IdOrderByIdDesc(Long memberId, Pageable pageable);
}
