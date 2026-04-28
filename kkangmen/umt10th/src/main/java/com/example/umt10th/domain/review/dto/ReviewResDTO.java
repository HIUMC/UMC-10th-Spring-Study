package com.example.umt10th.domain.review.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewResDTO {

    public record CreateReviewResultDto (
            LocalDateTime createdAt
    ){}
}
