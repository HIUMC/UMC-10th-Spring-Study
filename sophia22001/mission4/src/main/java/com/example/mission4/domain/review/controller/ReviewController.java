package com.example.mission4.domain.review.controller;

import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.exception.code.ReviewSuccessCode;
import com.example.mission4.domain.review.service.ReviewService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 마이페이지 리뷰 작성 // reviewId 반환
    @PostMapping("stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.MyPageReview> myPageReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.MyPageReview dto
            ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_REGISTERED;

        return ApiResponse.onSuccess(code, reviewService.myPageReview(dto));

    }
}
