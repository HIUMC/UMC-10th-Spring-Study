package com.example.umc10th.domain.review.dto;

public class ReviewRequestDTO {

    public record CreateReview(
            String title,
            String content,
            Float rate // 별점
    ) {}
}
