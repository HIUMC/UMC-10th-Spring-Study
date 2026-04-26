package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record Write(
            Long Id
    ){}
}
