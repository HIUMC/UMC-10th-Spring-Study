package com.example.demo.domain.review.dto;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class CreateReviewRequest {
        private Long storeId;
        private Float score;
        private String content;
    }

}
