package com.example.umc10th.domain.review.dto;
import lombok.Getter;

public class ReviewReqDTO {
    @Getter
    public static class CreateReviewDTO {
        private Integer score;
        private String content;
    }
}