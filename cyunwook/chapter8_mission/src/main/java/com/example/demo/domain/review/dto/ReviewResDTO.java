package com.example.demo.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewResDTO {
    private Long reviewId;
    private String content;
    private Float star;
    private LocalDateTime createdAt;

    @Getter
    @Builder
    public static class GetReview {
        private Long reviewId;
        private Float star;
        private String content;
        private LocalDateTime createdAt;
    }

    // 커서 기반 페이지네이션 응답
    @Getter
    @Builder
    public static class ReviewCursorDTO {
        private List<GetReview> reviews;
        private Long nextCursor;   // 다음 조회 시작 기준 ID
        private boolean hasNext;   // 다음 페이지 존재 여부
    }
}
