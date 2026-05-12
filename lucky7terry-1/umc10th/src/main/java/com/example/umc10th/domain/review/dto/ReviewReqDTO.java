package com.example.umc10th.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReviewRequest(
            Long memberId,
            ReviewDetailDto reviewDetailDto,
            List<String> images
    ) {
    }

    public record ReviewDetailDto(
            Double rating,
            String content
    ) {
    }
}
