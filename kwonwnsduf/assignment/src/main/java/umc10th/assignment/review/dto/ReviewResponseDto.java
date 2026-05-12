package umc10th.assignment.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDto {
    @Builder
    public record CreateReview(
            Long reviewId,
            Long storeId,
            Long memberId,
            Float star,
            String content,
            LocalDateTime createdAt
    ) {
    }
    @Builder
    public record GetMyReview(
            Long reviewId,
            String storeName,
            Float star,
            String content,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CursorPagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
    }
    }
