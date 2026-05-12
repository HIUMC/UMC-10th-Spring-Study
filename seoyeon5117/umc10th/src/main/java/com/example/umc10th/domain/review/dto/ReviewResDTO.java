package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record UpdateReview(
            Long reviewId,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record GetReview(
            Long reviewId,
            String memberNickname,
            String content,
            Float star,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String replyContent,
            LocalDateTime replyCreatedAt
    ) {
    }

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}