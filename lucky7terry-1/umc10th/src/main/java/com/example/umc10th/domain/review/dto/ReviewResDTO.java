package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            Double star,
            String content,
            LocalDateTime createdAt
    ) {}

    // 내가 쓴 리뷰 조회
    @Builder
    public record GetReviewDTO(
            Long reviewId,
            Long memberId,
            Double star,
            String content
    ) {}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}

