package umc10th.assignment.review.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import umc10th.assignment.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Long countByUserMemberId(Long memberId);
}