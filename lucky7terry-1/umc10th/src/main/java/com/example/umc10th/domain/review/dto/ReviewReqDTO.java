package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReviewRequest(
            Long memberId,

            @NotNull(message = "별점을 입력하세요.")
            Double star,

            @NotBlank(message = "리뷰 내용을 입력하세요.")
            String content
    ) {}
}
