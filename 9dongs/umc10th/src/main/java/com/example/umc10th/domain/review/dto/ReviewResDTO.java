package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record MyReviewDTO(
            Long reviewId,
            String restaurantName,
            String content,
            Float star,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
    }
}
