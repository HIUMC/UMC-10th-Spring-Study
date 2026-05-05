package com.example.week4.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReviewRequest(
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId,

            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId,

            @NotNull(message = "평점은 필수입니다.")
            @Min(value = 1, message = "평점은 1점 이상이어야 합니다.")
            @Max(value = 5, message = "평점은 5점 이하여야 합니다.")
            Integer rating,

            String reviewContent
    ) {
    }

    // 유저가 작성한 리뷰 조회
    public record UserReviewListRequest(
            @NotNull(message = "유저 ID는 필수입니다.")
            Long userId
    ) {
    }

    // 가게 리뷰 조회
    public record StoreReviewListRequest(
            @NotNull(message = "가게 ID는 필수입니다.")
            Long storeId
    ) {
    }
}