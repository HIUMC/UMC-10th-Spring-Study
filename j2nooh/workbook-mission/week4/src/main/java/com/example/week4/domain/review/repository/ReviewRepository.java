package com.example.week4.domain.review.repository;

import com.example.week4.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}