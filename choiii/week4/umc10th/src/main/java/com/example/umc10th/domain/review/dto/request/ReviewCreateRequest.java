package com.example.umc10th.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ReviewCreateRequest(
        @NotNull Long storeId,
        @NotNull @Min(1) @Max(5) Integer rating,
        String reviewContent,
        List<String> imageUrls
) {}
