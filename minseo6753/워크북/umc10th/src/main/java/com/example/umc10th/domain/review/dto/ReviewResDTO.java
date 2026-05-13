package com.example.umc10th.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record Info(
            Long id,
            String memberNickname,
            Integer star,
            String content,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record Slice<T>(
            List<T> data,
            Boolean hasNext,
            Long nextCursor,
            Integer pageSize
    ) {
    }
}
