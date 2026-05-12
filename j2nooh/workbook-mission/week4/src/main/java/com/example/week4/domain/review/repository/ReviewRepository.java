package com.example.week4.domain.review.repository;

import com.example.week4.domain.review.entity.Review;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(
            value = """
                    select r
                    from Review r
                    join fetch r.user u
                    join fetch r.store s
                    where u.id = :userId
                    order by r.reviewCreatedAt desc
                    """,
            countQuery = """
                    select count(r)
                    from Review r
                    where r.user.id = :userId
                    """
    )
    Page<Review> findUserReviews(
            @Param("userId") Long userId,
            Pageable pageable
    );

    @Query(
            value = """
                    select r
                    from Review r
                    join fetch r.user u
                    join fetch r.store s
                    where s.id = :storeId
                    order by r.reviewCreatedAt desc
                    """,
            countQuery = """
                    select count(r)
                    from Review r
                    where r.store.id = :storeId
                    """
    )
    Page<Review> findStoreReviews(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 ID 순 커서 조회
    @Query("""
            select r
            from Review r
            join fetch r.user u
            join fetch r.store s
            where u.id = :userId
              and (:cursorId = -1 or r.id < :cursorId)
            order by r.id desc
            """)
    List<Review> findUserReviewByIdCursor(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 별점 순 커서 조회
    @Query("""
            select r
            from Review r
            join fetch r.user u
            join fetch r.store s
            where u.id = :userId
              and (
                          :cursorRating = -1
                          or r.rating < :cursorRating 
                          or (r.rating = :cursorRating and r.id < :cursorId)
              )
            order by r.rating desc, r.id desc
            """)
    List<Review> findUserReviewByRatingCursor(
            @Param("userId") Long userId,
            @Param("cursorRating") Integer cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}