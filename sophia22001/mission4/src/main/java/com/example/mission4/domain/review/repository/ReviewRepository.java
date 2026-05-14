package com.example.mission4.domain.review.repository;

import com.example.mission4.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByMemberId(Long memberId);

    Slice<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor, PageRequest pageRequest);

    Slice<Review> findAllByMemberIdOrderByIdDesc(Long memberId, PageRequest pageRequest);

}
