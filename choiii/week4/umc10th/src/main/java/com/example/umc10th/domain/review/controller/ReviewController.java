package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.request.ReviewCreateRequest;
import com.example.umc10th.domain.review.dto.response.ReviewResponse;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.api.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/users/{userId}/reviews")
    public ApiResponse<ReviewResponse> createReview(@PathVariable Long userId, @RequestBody @Valid ReviewCreateRequest request) {
        return ApiResponse.created(reviewService.createReview(userId, request));
    }

    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<List<ReviewResponse>> getStoreReviews(@PathVariable Long storeId) {
        return ApiResponse.ok(reviewService.getStoreReviews(storeId));
    }
}
