package com.example.demo.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewReqDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class CreateReviewDTO {

        @Min(0)
        @Max(5)
        private Integer rating;

        @NotBlank
        private String title;

        @NotBlank
        private String content;

        private String photoURL;
    }
}
