package com.example.umt10th.domain.review.repository;

import com.example.umt10th.domain.review.entity.Review;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor, PageRequest pageRequest);

    @Query("select r from Review r where r.member.id = :memberId and (r.rating < :ratingCursor or (r.rating = :ratingCursor and r.id < :idCursor)) order by r.rating desc, r.id desc")
    Slice<Review> findReviewsByRating(Long memberId, long idCursor, float ratingCursor, PageRequest pageRequest);

    Slice<Review> findReviewsByMember_IdOrderByIdDesc(Long memberId, PageRequest pageRequest);

    Slice<Review> findReviewsByMember_IdOrderByRatingDescIdDesc(Long memberId, PageRequest pageRequest);
}
