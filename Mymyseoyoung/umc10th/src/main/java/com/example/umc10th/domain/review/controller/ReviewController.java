package com.example.umc10th.domain.review.controller;


import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    //마이페이지 리뷰 작성
    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid ReviewRequestDTO.CreateReview request) {


        ReviewResponseDTO.CreateResultDTO response = reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, response);
    }

    //내가 생성한 리뷰들 조회하기 < id 순 / 별점 순 >
    @PostMapping
    public ApiResponse<ReviewResponseDTO.Pagination<ReviewResponseDTO.getMyReviewDTO>> getMyReviews(
            @RequestBody @Valid MemberRequestDTO.getMyReviewRequest request)
    {
        ReviewResponseDTO.Pagination<ReviewResponseDTO.getMyReviewDTO> response =
                reviewService.getMyReviews(
                        request.memberId(),
                        request.cursor(),
                        request.sortType(),
                        request.pageSize()
                );

        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_FOUND, response);
    }


}
