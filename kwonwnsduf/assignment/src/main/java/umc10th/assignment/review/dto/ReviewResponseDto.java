package umc10th.assignment.review.dto;

import lombok.Builder;

public class ReviewResponseDto {
    @Builder
    public record CreateReview(
            Long reviewId,
            Long missionId,
            String content,
            Integer score
    ) {
    }
    }
