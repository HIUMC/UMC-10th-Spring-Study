package com.example.umc10th.domain.review.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewResponse(
        Long reviewId,
        Long userId,
        String userName,
        Long storeId,
        String storeName,
        Integer rating,
        String reviewContent,
        List<String> imageUrls,
        LocalDateTime createdAt
) {}
