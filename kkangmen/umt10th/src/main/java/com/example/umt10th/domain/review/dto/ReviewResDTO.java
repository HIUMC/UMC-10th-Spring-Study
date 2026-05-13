package com.example.umt10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class ReviewResDTO {

    // 리뷰 생성
    public record CreateReviewResultDto (
            LocalDateTime createdAt
    ){}

    // 나의 리뷰 조회
    @Builder
    public record GetReview(
            Long reviewId,
            Long memberId,
            String name,
            String content,
            Float rating
    ){}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
