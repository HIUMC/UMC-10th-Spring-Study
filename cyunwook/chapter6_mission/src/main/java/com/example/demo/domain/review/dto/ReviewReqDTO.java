package com.example.demo.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewReqDTO {

    @NotNull
    private Long storeId;

    @NotNull
    private Long memberId;

    @NotBlank
    private String content;

    @NotNull
    @DecimalMin("0.0") @DecimalMax("5.0")
    private Float star;
}