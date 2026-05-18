package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor, PageRequest pageRequest);

    @Query("select r from Review r " +
            "where r.member.id = :memberId " +
            "and (r.star < :starCursor " +
            "or (r.star = :starCursor and r.id < :idCursor))")
    Slice<Review> findReviewsByStar(Long memberId, long idCursor, Double starCursor, PageRequest pageRequest);

    Slice<Review> findReviewsByMember_IdOrderByIdDesc(Long memberId, PageRequest pageRequest);

    Slice<Review> findReviewsByMember_IdOrderByStarDesc(Long memberId, PageRequest pageRequest);
}
