package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

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
}
