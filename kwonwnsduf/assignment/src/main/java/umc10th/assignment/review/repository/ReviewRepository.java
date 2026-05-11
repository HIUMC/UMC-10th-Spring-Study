package umc10th.assignment.review.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc10th.assignment.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Long countByUserMemberId(Long memberId);

    // 내가 작성한 리뷰 - ID 기준 첫 조회
    @Query("""
        select r
        from Review r
        join fetch r.store s
        where r.user.memberId = :memberId
        order by r.reviewId desc
        """)
    List<Review> findMyReviewsOrderByIdDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 - ID 커서 이후 조회
    @Query("""
        select r
        from Review r
        join fetch r.store s
        where r.user.memberId = :memberId
          and r.reviewId < :cursorId
        order by r.reviewId desc
        """)
    List<Review> findMyReviewsByIdCursor(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 - 별점 기준 첫 조회
    @Query("""
        select r
        from Review r
        join fetch r.store s
        where r.user.memberId = :memberId
        order by r.star desc, r.reviewId desc
        """)
    List<Review> findMyReviewsOrderByStarDesc(
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 내가 작성한 리뷰 - 별점 커서 이후 조회
    @Query("""
        select r
        from Review r
        join fetch r.store s
        where r.user.memberId = :memberId
          and (
                r.star < :cursorStar
                or (r.star = :cursorStar and r.reviewId < :cursorId)
          )
        order by r.star desc, r.reviewId desc
        """)
    List<Review> findMyReviewsByStarCursor(
            @Param("memberId") Long memberId,
            @Param("cursorStar") Float cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}