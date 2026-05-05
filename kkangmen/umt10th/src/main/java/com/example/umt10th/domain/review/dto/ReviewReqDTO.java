package com.example.umt10th.domain.review.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class ReviewReqDTO {

    public record CreateReviewDto(
            ReviewDetailDto reviewDetailDto,
            List<String> images
    ){}

    public record ReviewDetailDto(
            Float rating,
            String content
    ){}
}
