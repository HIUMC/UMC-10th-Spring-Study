package umc10th.assignment.review.dto;

public class ReviewRequestDto {
    public record CreateReview(
            Long memberId,
            Float star,
            String content
    ) {
    }
}
