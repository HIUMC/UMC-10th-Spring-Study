package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /* ───────────────────────────────────────────────────────────────
       리뷰 작성  POST /api/{storeId}/review
       ─────────────────────────────────────────────────────────────── */
    @PostMapping("/{storeId}/review")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResDTO.ReviewWriteResDTO> writeReview(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewReqDTO.ReviewWriteReqDTO request) {

        ReviewResDTO.ReviewWriteResDTO response = reviewService.writeReview(storeId, request);
        return ApiResponse.onSuccess(response);
    }

    /* ───────────────────────────────────────────────────────────────
       내 리뷰 목록 조회  GET /api/members/{memberId}/reviews
       ?sort=id|star  &cursor=xxx  &size=10
       ─────────────────────────────────────────────────────────────── */
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPagination> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "10") @Min(1) @Max(50) int size) {

        ReviewResDTO.ReviewPagination response = reviewService.getMyReviews(memberId, sort, cursor, size);
        return ApiResponse.onSuccess(response);
    }
}
