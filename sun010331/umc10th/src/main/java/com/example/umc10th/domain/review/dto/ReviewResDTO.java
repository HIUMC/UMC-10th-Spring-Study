package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {


    @Builder
    public record StoreReviewRes (
            Long id
    ){

    }

    @Getter
    @Builder
    public static class GetReview {
        private Long reviewId;
        private Float star;
        private String content;
        private LocalDateTime createdAt;
    }


    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}



    @Getter
    @Builder
    public static class ReviewCursorDTO {
        private List<GetReview> reviews;
        private Long nextCursor;
        private boolean hasNext;
    }
}


