package com.example.umc10th.domain.review.dto;

import com.example.umc10th.domain.review.entity.ReviewImage;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record WriteResult(
            Long id
    ){}

    @Builder
    public record getMyReviews(
            Long memberId,
            BigDecimal starRating,
            String content,
            List<String> reviewImageList
    ) {}

    @Builder
    public record Pagination<T> (
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
