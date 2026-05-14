package com.example.mission4.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record MyPageReview(
            Long reviewId
    ){}

    @Builder
    public record GetMyReview (
            Long reviewId,
            String storeName,
            String username,
            Integer star,
            String content,
            LocalDateTime createdAt
    ){}

    @Builder
    public record Pagination<T> (
            List<T> data,
            Boolean hasNext, // 다음 데이터가 있는지
            String nextCursor, // 다음 커서는 무엇인지
            Integer pageSize // 불러온 데이터 수는 몇 개인지
    ){}
}
