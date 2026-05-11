package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.exception.code.ReviewSuccessCode;
import com.example.demo.domain.review.service.ReviewService;
import com.example.demo.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @Valid @RequestBody ReviewReqDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, reviewService.createReview(request));
    }

    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "id") String query
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_FOUND,
                reviewService.getStoreReviews(storeId, pageSize, cursor, query)
        );
    }

}
