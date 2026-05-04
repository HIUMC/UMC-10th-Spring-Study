package umc10th.assignment.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResponseDto {
    @Builder
    public record CreateReview(
            Long reviewId,
            Long storeId,
            Long memberId,
            Float star,
            String content,
            LocalDateTime createdAt
    ) {
    }
    }
