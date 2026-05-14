package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewReqDTO {
    public record CreateReviewDTO(
            @NotNull(message = "별점은 필수 입력값입니다.")
            Float star,
            
            @NotBlank(message = "리뷰 내용은 필수 입력값입니다.")
            @Size(min = 10, max = 500, message = "리뷰 내용은 10자 이상, 500자 이하여야 합니다.")
            String content
    ) {
    }
}
