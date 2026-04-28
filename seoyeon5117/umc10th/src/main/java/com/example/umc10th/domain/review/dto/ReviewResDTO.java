package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record UpdateReview(
            Long reviewId,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record GetReview(
            Long reviewId,
            String content,
            Float star,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}