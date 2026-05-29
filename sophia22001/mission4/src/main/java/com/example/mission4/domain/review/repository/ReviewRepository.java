package com.example.mission4.domain.review.repository;

import com.example.mission4.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByMemberId(Long memberId);

    // id 정렬
    Slice<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor, Pageable pageable);
    Slice<Review> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    // star 정렬 - 커서 X
    Slice<Review> findAllByMemberIdOrderByStarDescIdDesc(Long memberId, Pageable pageable);

    // star 정렬 - 커서 O
    // (star < :star) OR (star = :star AND id < :id)
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.star < :starCursor OR (r.star = :starCursor AND r.id < :idCursor))" +
            "ORDER BY r.star DESC, r.id DESC"
    )
    Slice<Review> findAllByMemberIdAndStarCursor(@Param("memberId") Long memberId,
                                                 @Param("starCursor") Integer starCursor,
                                                 @Param("idCursor") Long idCursor,
                                                 Pageable pageable);

}
