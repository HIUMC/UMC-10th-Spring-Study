package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    // 리뷰 작성 API 명세에 맞춰 RequestBody를 DTO로 받고 공통 응답 형식으로 감싸 반환한다.

    @PostMapping
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @RequestBody ReviewRequestDTO.CreateReviewRequest request
    ) {
        ReviewResponseDTO.CreateReviewResultDTO response = ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(1L)
                .storeId(request.getStoreId())
                .score(request.getScore())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(response);
    }
}
