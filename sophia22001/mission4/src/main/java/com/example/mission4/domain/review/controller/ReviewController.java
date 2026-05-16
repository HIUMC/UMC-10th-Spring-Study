package com.example.mission4.domain.review.controller;

import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.exception.code.ReviewSuccessCode;
import com.example.mission4.domain.review.service.ReviewService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 마이페이지 리뷰 작성 // reviewId 반환
    @PostMapping("stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.MyPageReview> myPageReview(
            @RequestParam Long memberId,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.MyPageReview dto
            ) {

        BaseSuccessCode code = ReviewSuccessCode.REVIEW_REGISTERED;
        return ApiResponse.onSuccess(code, reviewService.myPageReview(memberId, storeId, dto));

    }

    // 나의 리뷰 조회하기
    @GetMapping("reviews/my")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetMyReview>> getMyReview(
            @RequestParam Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam(required = false) String query
    ) {
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_FOUND;
        return ApiResponse.onSuccess(code, reviewService.getMyReview(memberId, pageSize, cursor, query));
    }
}
