package umc10th.assignment.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
import umc10th.assignment.review.dto.ReviewRequestDto;
import umc10th.assignment.review.dto.ReviewResponseDto;
import umc10th.assignment.review.exception.code.ReviewSuccessCode;
import umc10th.assignment.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/{missionId}/reviews")
    public ApiResponse<ReviewResponseDto.CreateReview> createReview(
            @PathVariable Long missionId,
            @Valid @RequestBody ReviewRequestDto.CreateReview dto
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATE_REVIEW;

        return ApiResponse.onSuccess(
                code,
                reviewService.createReview(missionId, dto)
        );
    }

    // 내가 생성한 리뷰들 조회 - 커서 기반 페이지네이션
    @PostMapping("/members/me/reviews")
    public ApiResponse<ReviewResponseDto.CursorPagination<ReviewResponseDto.GetMyReview>> getMyReviews(
            @Valid @RequestBody ReviewRequestDto.GetMyReview dto,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam(defaultValue = "id") String query
    ) {
        BaseSuccessCode code = ReviewSuccessCode.GET_REVIEWS;

        return ApiResponse.onSuccess(
                code,
                reviewService.getMyReviews(dto, pageSize, cursor, query)
        );
    }
}

