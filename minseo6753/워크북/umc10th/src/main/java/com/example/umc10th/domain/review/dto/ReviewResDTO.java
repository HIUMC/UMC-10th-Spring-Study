package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record Info(
            Long id,
            String content,
            Integer star
    ) {
    }
}
