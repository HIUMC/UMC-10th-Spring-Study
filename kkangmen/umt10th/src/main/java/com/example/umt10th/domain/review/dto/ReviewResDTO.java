package com.example.umt10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class ReviewResDTO {

    public record CreateReviewResultDto (
            LocalDateTime createdAt
    ){}
}
