package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
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

    // 내가 작성한 리뷰 조회 ( 커서 기반 )
    @GetMapping("members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewCursorDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.OK,
                reviewService.getMyReviews(memberId, cursor, size, sort));
    }


}
