package com.example.demo.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewRequest {
        @NotNull(message = "가게 ID는 필수입니다.")
        @Positive(message = "가게 ID는 양수여야 합니다.")
        private Long storeId;

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "0.0", message = "별점은 0점 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5점 이하여야 합니다.")
        private Float score;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;
    }

}
