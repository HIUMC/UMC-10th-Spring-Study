package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStoreId(Long storeId, Pageable pageable);

    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.store s
        WHERE r.user.id = :userId
          AND (:cursorId IS NULL OR r.id < :cursorId)
        ORDER BY r.id DESC
    """)
    List<Review> findMyReviewsByIdCursor(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @Query("""
        SELECT r
        FROM Review r
        JOIN FETCH r.store s
        WHERE r.user.id = :userId
          AND (
                :cursorRating IS NULL
                OR r.rating < :cursorRating
                OR (r.rating = :cursorRating AND r.id < :cursorId)
          )
        ORDER BY r.rating DESC, r.id DESC
    """)
    List<Review> findMyReviewsByRatingCursor(
            @Param("userId") Long userId,
            @Param("cursorRating") Integer cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}

