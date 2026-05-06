package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
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
}
