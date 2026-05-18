package com.example.umt10th.domain.review.controller;

import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.dto.ReviewResDTO;
import com.example.umt10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umt10th.domain.review.service.ReviewService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    /***
     * 리뷰 생성하기
     * @param dto
     * @param storeId
     * @return
     */
    @PostMapping("v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewResultDto> createReview(
            @RequestBody ReviewReqDTO.CreateReviewDto dto,
            @PathVariable("storeId") Long storeId
    ){
        BaseSuccessCode successCode = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(successCode, reviewService.createReview(dto, storeId, 1L));
    }

    /***
     * 내가 생성한 리뷰 조회하기
     */
    @PostMapping("v1/members/me/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getMyReview(
            @RequestBody @Valid ReviewReqDTO.MyReview dto,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String sortBy
    ){

        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviews(dto, pageSize, cursor, sortBy));
    }
}
