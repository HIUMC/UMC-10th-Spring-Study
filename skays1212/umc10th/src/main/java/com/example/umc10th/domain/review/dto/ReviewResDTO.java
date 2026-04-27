package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {

    /* ───────────── 리뷰 작성 ───────────── */
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ReviewWriteResDTO {
        private Long reviewId;
        private Long storeId;
        private Integer rating;
        private String reviewContent;
        private String photoUrl;
    }
}
