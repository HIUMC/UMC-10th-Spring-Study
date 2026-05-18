package com.example.week4.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class ReviewResponse {

        private Long reviewId;
        private Long userId;
        private Long storeId;
        private Integer rating;
        private String reviewContent;
        private LocalDateTime reviewCreatedAt;
    }

    @Getter
    @Builder
    public static class ReviewListResponse {
        private List<ReviewResponse> reviews;
    }

    @Getter
    @Builder
    public static class UserReviewCursorListResponse {
        private List<ReviewResponse> reviews;
        private Boolean hasNext;
        private String nextCursor;
        private Integer pageSize;
        private Integer currentCount;
    }
}