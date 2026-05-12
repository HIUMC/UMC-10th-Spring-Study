package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.enums.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 등록
    @PostMapping("/store/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATE;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, dto));
    }

    // 리뷰 조회
    @GetMapping("/member/{memberId}/reviews/{reviewId}")
    public ApiResponse<ReviewResDTO.GetReview> getReview(
            @PathVariable Long reviewId
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET;
        return ApiResponse.onSuccess(code, reviewService.getReview(reviewId));
    }

    // 내 리뷰 목록 조회 (리뷰 + 리뷰 답글)
    @Operation(summary = "내 리뷰 목록 조회", description = "커서 기반 페이지네이션으로 리뷰와 답글을 함께 조회합니다. query: id(최신순), star(별점순)")
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "-1") String nextCursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET;

        return ApiResponse.onSuccess(code, reviewService.getReviewByMemberId(memberId, pageSize, nextCursor, query));
    }

    // 리뷰 수정
    @PutMapping("members/{memberId}/review/{reviewId}")
    public ApiResponse<ReviewResDTO.UpdateReview> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_UPDATE;
        return ApiResponse.onSuccess(code, reviewService.updateReview(reviewId, dto));
    }

    // 가게 id로 리뷰 목록 조회 (리뷰 + 리뷰 답글)
    @GetMapping("/store/{storeId}/reviews")
    public ApiResponse<Page<ReviewResDTO.GetReview>> getStoreReviews(
            @PathVariable Long storeId,
            Pageable pageable
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET;

        return ApiResponse.onSuccess(code, reviewService.getReviewByStoreId(storeId, pageable));
    }
}