package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.request.MyReviewCursorRequest;
import com.example.umc10th.domain.review.dto.request.ReviewCreateRequest;
import com.example.umc10th.domain.review.dto.response.MyReviewCursorResponse;
import com.example.umc10th.domain.review.dto.response.ReviewResponse;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.api.ApiResponse;
import com.example.umc10th.global.api.code.CommonSuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/users/{userId}/reviews")
    public ApiResponse<ReviewResponse> createReview(@PathVariable Long userId, @RequestBody @Valid ReviewCreateRequest request) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.CREATED,
                reviewService.createReview(userId, request)
        );
    }

    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<List<ReviewResponse>> getStoreReviews(@PathVariable Long storeId, Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.OK,
                reviewService.getStoreReviews(storeId, pageable)
        );
    }

    @PostMapping("/reviews/my")
    public ApiResponse<MyReviewCursorResponse> getMyReviews(
            @RequestBody @Valid MyReviewCursorRequest request
    ) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.OK,
                reviewService.getMyReviews(request)
        );
    }
}
