package com.example.umc10th.domain.review.dto;

public class ReviewReqDTO {

    public record Create(
            Integer star,
            String content
    ) {
    }
}
