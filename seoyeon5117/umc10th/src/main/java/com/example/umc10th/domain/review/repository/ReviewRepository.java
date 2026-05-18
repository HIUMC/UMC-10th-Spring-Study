package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select r
            from Review r
            left join fetch r.reply
            where r.store.id = :storeId
        """)
    Page<Review> findByStoreId(
            @Param("storeId") Long storeId,
            Pageable pageable
    );

    @Query("""
            select r from Review r
            left join fetch r.member
            left join fetch r.reply
            where r.member.id = :memberId and r.id < :idCursor
            order by r.id desc
            """)
    Slice<Review> findByMemberIdAndIdCursor(
            @Param("memberId") Long memberId,
            @Param("idCursor") long idCursor,
            PageRequest pageRequest
    );

    @Query("""
            select r from Review r
            left join fetch r.member
            left join fetch r.reply
            where r.member.id = :memberId
              and (r.star < :star or (r.star = :star and r.id < :idCursor))
            order by r.star desc, r.id desc
            """)
    Slice<Review> findByMemberIdAndStarCursor(
            @Param("memberId") Long memberId,
            @Param("star") float star,
            @Param("idCursor") long idCursor,
            PageRequest pageRequest
    );

    @Query("""
            select r from Review r
            left join fetch r.member
            left join fetch r.reply
            where r.member.id = :memberId
            order by r.id desc
            """)
    Slice<Review> findByMemberId(
            @Param("memberId") Long memberId,
            PageRequest pageRequest
    );

    @Query("""
            select r from Review r
            left join fetch r.member
            left join fetch r.reply
            where r.member.id = :memberId
            order by r.star desc, r.id desc
            """)
    Slice<Review> findByMemberIdOrderByStar(
            @Param("memberId") Long memberId,
            PageRequest pageRequest
    );
}
