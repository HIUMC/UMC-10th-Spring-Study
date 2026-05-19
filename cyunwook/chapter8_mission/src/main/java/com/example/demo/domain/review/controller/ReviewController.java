package com.example.demo.domain.review.controller;

import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.exception.code.ReviewSuccessCode;
import com.example.demo.domain.review.service.ReviewService;
import com.example.demo.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    @Operation(summary = "리뷰 작성")
    public ResponseEntity<ReviewResDTO> createReview(
            @RequestBody @Valid ReviewReqDTO request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }

    @GetMapping("/v1/members/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 조회 (커서 기반)")
    public ApiResponse<ReviewResDTO.ReviewCursorDTO> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long cursor,  // 첫 조회 시 없어도 됨
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort  // "id" or "star"
    ) {
        return ApiResponse.onSuccess(
                ReviewSuccessCode.OK,
                reviewService.getMyReviews(memberId, cursor, size, sort));
    }
}
