package com.example.umt10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReviewDto(
            ReviewDetailDto reviewDetailDto,
            List<String> images
    ){}

    public record ReviewDetailDto(
            Double rating,
            String content
    ){}
}
