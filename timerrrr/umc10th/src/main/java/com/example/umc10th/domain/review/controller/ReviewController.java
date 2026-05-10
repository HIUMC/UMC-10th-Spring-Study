package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PostMapping("/members/{memberId}/stores/{storeId}/reviews")
    public ApiResponse<Void> createReview(
            @PathVariable Long memberId,
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReview dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        reviewService.createReview(memberId, storeId, dto);
        return ApiResponse.onSuccess(code, null);
    }

    // 내가 생성한 리뷰들 조회 - 커서 기반 페이지네이션
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.MyReviewInfo>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code,
                reviewService.getMyReviews(memberId, pageSize, cursor, query));
    }
}
