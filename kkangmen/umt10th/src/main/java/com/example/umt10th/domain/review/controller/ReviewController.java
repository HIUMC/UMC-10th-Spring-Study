package com.example.umt10th.domain.review.controller;

import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.dto.ReviewResDTO;
import com.example.umt10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umt10th.domain.review.service.ReviewService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDto> createReview(
            @RequestBody ReviewReqDTO.CreateReviewDto dto,
            @PathVariable("storeId") Long storeId
    ){
        BaseSuccessCode successCode = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(successCode, reviewService.saveReview(dto));
    }
}
