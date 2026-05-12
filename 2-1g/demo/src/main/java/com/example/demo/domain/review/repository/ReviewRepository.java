package com.example.demo.domain.review.repository;

import com.example.demo.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Review 엔티티를 다루는 레포지토리, PK타입은 Long
// JpaRepository를 상속받으면 save, findById, findAll, delete, count 같은 기본 메서드를 자동으로 생성해줌
// 따라서 직접 구현 클래스를 만들 필요가 없음 
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStoreId(Long storeId, Pageable pageable);

    // 첫 요청을 위한 cursor가 없는 버전
    List<Review> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // cursor가 있는 버전
    List<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, Long cursor, Pageable pageable);

    @Query("""
            select r from Review r
            where r.member.id = :memberId
            order by r.score desc, r.id desc
            """)
    List<Review> findAllByMemberIdOrderByScoreDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    @Query("""
            select r from Review r
            where r.member.id = :memberId
              and (
                  r.score < :cursorScore
                  or (r.score = :cursorScore and r.id < :cursorId)
              )
            order by r.score desc, r.id desc
            """)
    List<Review> findAllByMemberIdAndScoreCursorOrderByScoreDesc(
            @Param("memberId") Long memberId,
            @Param("cursorScore") Float cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
