package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewResDTO {

    // 내가 생성한 리뷰 정보 (사진 부분 제외)
    @Builder
    public record MyReviewInfo(
            Long reviewId,
            BigDecimal star,
            String reviewDetail,
            String storeName
    ) {}

    // 커서 기반 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}

}
