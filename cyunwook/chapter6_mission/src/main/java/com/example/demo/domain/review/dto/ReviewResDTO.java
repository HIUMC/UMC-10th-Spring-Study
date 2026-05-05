package com.example.demo.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResDTO {
    private Long reviewId;
    private String content;
    private Float star;
    private LocalDateTime createdAt;
}
