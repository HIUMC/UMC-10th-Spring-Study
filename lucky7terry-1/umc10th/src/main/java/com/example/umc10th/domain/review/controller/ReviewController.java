package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReviewRequest dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, dto));
    }

    // 내가 쓴 리뷰 조회
    @GetMapping("v1/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO>> getMyReviews(
        @PathVariable Long memberId,
        @RequestParam Integer pageSize,
        @RequestParam String cursor,
        @RequestParam String sort
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviews(memberId, pageSize, cursor, sort));
    }
}
