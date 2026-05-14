package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request) {
        return ApiResponse.onSuccess(reviewService.createReview(memberId, storeId, request));
    }

    @GetMapping("/users/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewSliceDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false, defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(memberId, cursor, sort, size));
    }
}