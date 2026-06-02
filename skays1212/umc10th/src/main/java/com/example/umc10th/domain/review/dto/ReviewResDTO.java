package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    /* ───────────── 리뷰 작성 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewWriteResDTO {
        private Long reviewId;
        private String reviewContent;
        private BigDecimal star;
        private Long storeId;
        private String photoUrl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetReview {
        private Long reviewId;
        private String storeName;
        private BigDecimal star;
        private String content;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewPagination {
        private List<GetReview> data;
        private Boolean hasNext;
        private String nextCursor;
        private Integer pageSize;
    }
}
