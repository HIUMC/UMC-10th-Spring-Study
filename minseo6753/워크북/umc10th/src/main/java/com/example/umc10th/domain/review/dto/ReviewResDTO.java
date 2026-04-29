package com.example.umc10th.domain.review.dto;

public class ReviewResDTO {

    public record Info(
            Long id,
            String content,
            Integer star
    ) {
    }
}
