package com.example.mission4.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record MyPageReview(
            Long reviewId
    ){}
}
