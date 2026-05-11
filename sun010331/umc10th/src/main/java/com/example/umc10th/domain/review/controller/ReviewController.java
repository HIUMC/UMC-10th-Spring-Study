package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.StoreReviewRes> createReview(
            @RequestParam Long memberId,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.storeReview request) {

        //서비스에서 storeId request 처리
        BaseSuccessCode code = ReviewSuccessCode.OK;

        return ApiResponse.onSuccess(code,reviewService.createReviews(storeId,memberId,request));

    }
}
