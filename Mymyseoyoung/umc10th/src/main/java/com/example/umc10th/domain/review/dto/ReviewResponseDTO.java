package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    public record CreateResultDTO(
            Long memberId,
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
