package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Slice<Review> findByMemberOrderByIdDesc(Member member, Pageable pageable);

    Slice<Review> findByMemberAndIdLessThanOrderByIdDesc(Member member, Long idIsLessThan, Pageable pageable);

    Slice<Review> findByMemberOrderByStarDescIdDesc(Member member, Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "WHERE r.member = :member " +
            "AND (r.star < :star OR (r.star = :star AND r.id < :id)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findNextSliceByMemberOrderByStar(Member member, int star, Long id, PageRequest pageRequest);
}
