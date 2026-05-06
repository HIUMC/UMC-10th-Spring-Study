package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            Integer score,
            String content
    ){}
}
