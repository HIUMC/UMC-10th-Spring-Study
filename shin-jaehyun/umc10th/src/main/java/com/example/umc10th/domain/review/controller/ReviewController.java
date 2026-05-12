package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/review")
    public ApiResponse<ReviewResDTO.WriteResult> write(
            @RequestParam Long memberId,
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.Write dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.write(memberId, storeId, dto));
    }

    @GetMapping("v1/reviews/my")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.getMyReviews>> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query,
            @Parameter(
                    description = "정렬 방식 (LATEST, OLDEST, RATING, ID)"
            )
            @RequestParam(required = false, defaultValue = "LATEST") String sort
    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getMyMissions(memberId, pageSize, cursor, query, sort));
    }
}
