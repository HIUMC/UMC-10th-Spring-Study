package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.service.ReviewService;
import global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    // 리뷰 작성 API 명세에 맞춰 RequestBody를 DTO로 받고 공통 응답 형식으로 감싸 반환한다.

    private static final Long TEMP_MEMBER_ID = 1L; // 임시 memberId

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @RequestBody ReviewRequestDTO.CreateReviewRequest request
    ) {
        return ApiResponse.onSuccess(reviewService.createReview(TEMP_MEMBER_ID, request));
    }
}
