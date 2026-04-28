package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReview(
            Float star,
            Long storeId,
            String content
    ) {
    }
}