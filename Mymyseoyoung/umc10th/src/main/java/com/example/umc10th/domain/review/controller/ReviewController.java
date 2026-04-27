package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/missions")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


    //마이페이지 리뷰 작성
    @PostMapping("/{missionId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody ReviewRequestDTO.CreateReview request) {

        // TODO: ReviewSuccessCode.REVIEW_CREATED 추가 후 사용
        return ApiResponse.onSuccess(null, null);
    }
}
