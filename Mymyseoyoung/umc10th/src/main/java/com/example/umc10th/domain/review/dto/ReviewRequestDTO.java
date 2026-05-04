package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewRequestDTO {

    public record CreateReview(

            @NotBlank String content,
            @NotNull @Min(0) @Max(5)  Float rate // 별점
    ) {}
}
