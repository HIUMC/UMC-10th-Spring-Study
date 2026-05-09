package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 마이페이지 리뷰 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<Void> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReview dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        reviewService.createReview(storeId, dto);
        return ApiResponse.onSuccess(code, null);
    }
}
