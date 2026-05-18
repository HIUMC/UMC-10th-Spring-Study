package com.example.umt10th.domain.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public class ReviewReqDTO {

    // 리뷰 생성
    public record CreateReviewDto(
            ReviewDetailDto reviewDetailDto,
            List<String> images
    ){}

    public record ReviewDetailDto(
            Float rating,
            String content
    ){}

    // 내가 생성한 리뷰 조회
    public record MyReview(

            @NotNull
            Long memberId
    ){}
}
