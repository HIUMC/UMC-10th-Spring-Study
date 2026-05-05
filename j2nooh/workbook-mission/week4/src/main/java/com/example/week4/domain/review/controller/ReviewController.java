package com.example.week4.domain.review.controller;

import com.example.week4.domain.review.dto.ReviewReqDTO;
import com.example.week4.domain.review.dto.ReviewResDTO;
import com.example.week4.domain.review.exception.code.ReviewSuccessCode;
import com.example.week4.domain.review.service.ReviewService;
import com.example.week4.global.apiPayload.ApiResponse;
import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/v1/reviews")
    public ApiResponse<ReviewResDTO.ReviewResponse> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateReviewRequest dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_REVIEW;
        ReviewResDTO.ReviewResponse response = reviewService.createReview(dto);

        return ApiResponse.onSuccess(code, response);
    }

    // 유저가 작성한 리뷰 조회
    @PostMapping("/v1/reviews/user")
    public ApiResponse<ReviewResDTO.ReviewListResponse> getUserReview(
            @RequestBody @Valid ReviewReqDTO.UserReviewListRequest dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.GET_USER_REVIEWS;
        ReviewResDTO.ReviewListResponse response = reviewService.getUserReviews(dto);

        return ApiResponse.onSuccess(code, response);
    }

    // 가게 리뷰 조회
    @PostMapping("/v1/reviews/store")
    public ApiResponse<ReviewResDTO.ReviewListResponse> getStoreReview(
            @RequestBody @Valid ReviewReqDTO.StoreReviewListRequest dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.GET_STORE_REVIEWS;
        ReviewResDTO.ReviewListResponse response = reviewService.getStoreReviews(dto);

        return ApiResponse.onSuccess(code, response);
    }

}
