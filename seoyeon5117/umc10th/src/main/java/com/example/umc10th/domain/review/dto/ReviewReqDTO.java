package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReview(
            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "1.0", message = "별점은 1점 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5점 이하여야 합니다.")
            Float star,
            @NotBlank(message = "리뷰 내용은 빈칸일 수 없습니다.")
            String content
    ) {
    }
}