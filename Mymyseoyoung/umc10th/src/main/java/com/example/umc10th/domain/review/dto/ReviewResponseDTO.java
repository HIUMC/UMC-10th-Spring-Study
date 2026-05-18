package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    public record CreateResultDTO(
            Long memberId,
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record getMyReviewDTO
            (
                    Long memberId,
                    String name,
                    Long reviewId,
                    LocalDateTime createdAt,
                    Float rate,
                    String content

            ){}

    @Builder
    //페이지네이션 틀
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
