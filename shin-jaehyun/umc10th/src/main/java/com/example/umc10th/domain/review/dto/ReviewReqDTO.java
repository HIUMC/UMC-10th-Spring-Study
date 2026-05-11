package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record Write(
            @NotNull(message = "별점은 필수입니다.")
            BigDecimal starRating,
            @NotBlank(message = "리뷰는 빈칸일 수 없습니다.")
            String content,
            List<String> imgUrls
    ) {}
}
