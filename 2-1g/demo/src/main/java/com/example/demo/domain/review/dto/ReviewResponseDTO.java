package com.example.demo.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private Long storeId;
        private Float score;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewPreviewDTO {
        private Long reviewId;
        private Long storeId;
        private Float score;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyReviewListResultDTO {
        private List<MyReviewPreviewDTO> reviewList;
        private Long nextCursor;
        private Boolean hasNext;
    }
}
