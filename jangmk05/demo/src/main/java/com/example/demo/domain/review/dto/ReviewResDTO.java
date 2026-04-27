package com.example.demo.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long id;
        private Integer rating;
        private String title;
        private String content;
        private String photoURL;
    }
}
