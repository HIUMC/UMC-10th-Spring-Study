package umc10th.assignment.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.review.dto.ReviewRequestDto;
import umc10th.assignment.review.dto.ReviewResponseDto;
import umc10th.assignment.review.exception.code.ReviewSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class ReviewController {
    @PostMapping("/{missionId}/reviews")
    public ApiResponse<ReviewResponseDto.CreateReview> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewRequestDto.CreateReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_REVIEW;

        ReviewResponseDto.CreateReview response = ReviewResponseDto.CreateReview.builder()
                .reviewId(1L)
                .missionId(missionId)
                .content(dto.content())
                .score(dto.score())
                .build();

        return ApiResponse.onSuccess(code, response);
    }
}
