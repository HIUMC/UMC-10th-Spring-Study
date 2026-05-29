package com.example.umc10th.domain.review.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

public class ReviewResDTO {

    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long reviewId;
    }

    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class ReviewDetailDTO {
        private Long reviewId;
        private String content;
        private BigDecimal star;
    }

    // 커서 기반 페이지네이션 응답 DTO
    @Builder @Getter @NoArgsConstructor @AllArgsConstructor
    public static class ReviewSliceDTO {
        private List<ReviewDetailDTO> reviewList;
        private Boolean hasNext;
        private String nextCursor;
        private Integer size;
    }
}