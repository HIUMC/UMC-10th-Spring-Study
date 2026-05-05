package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.enums.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATE;
        return ApiResponse.onSuccess(code, reviewService.createReview(dto));
    }

    // 리뷰 조회
    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResDTO.GetReview> getReview(
            @PathVariable Long reviewId
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET;
        return ApiResponse.onSuccess(code, reviewService.getReview(reviewId));
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReview> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_UPDATE;
        return ApiResponse.onSuccess(code, reviewService.updateReview(reviewId, dto));
    }

    // 가게 id로 리뷰 목록 조회 (리뷰 + 리뷰 답글)
    @GetMapping("/{storeId}")
    public ApiResponse<Page<ReviewResDTO.GetReview>> getStoreReview(
            @PathVariable Long storeId,
            Pageable pageable
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET;

        return ApiResponse.onSuccess(code, reviewService.getReviewByStoreId(storeId, pageable));
    }
}