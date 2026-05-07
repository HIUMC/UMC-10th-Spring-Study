package com.example.umc10th.domain.review.dto;
import lombok.*;

public class ReviewResDTO {
    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long reviewId;
    }
}