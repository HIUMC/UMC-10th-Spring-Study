package umc10th.assignment.review.dto;

public class ReviewRequestDto {
    public record CreateReview(
            String content,
            Integer score
    ) {
    }
}
