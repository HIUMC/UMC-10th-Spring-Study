package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReview(
            @NotNull(message = "별점 입력은 필수입니다.")
            BigDecimal star,
            String reviewDetail,
            List<String> imageUrls
    ) {}
}
