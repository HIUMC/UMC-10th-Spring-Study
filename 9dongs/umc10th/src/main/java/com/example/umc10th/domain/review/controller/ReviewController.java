package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "restaurantId") Long restaurantId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        Long memberId = 1L;
        reviewService.createReview(memberId, restaurantId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/members/me/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.MyReviewDTO>> getMyReviews(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(name = "cursor", required = false) String cursor,
            @RequestParam(name = "query", defaultValue = "id") String query,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        ReviewResDTO.Pagination<ReviewResDTO.MyReviewDTO> result = reviewService.getMyReviews(memberId, cursor, query, size);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

}
