package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReview request) {
        return Review.builder()
        .content(request.content())
                .rate(request.rate())
                .build();
    }

    public static ReviewResponseDTO.getMyReviewDTO toGetMyReviewDTO(Review review) {
        return ReviewResponseDTO.getMyReviewDTO.builder()
                .memberId(review.getMember().getId())
                        .name(review.getMember().getName())
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .rate(review.getRate())
                .content(review.getContent())
                .build();

    }

    public static ReviewResponseDTO.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResponseDTO.CreateResultDTO.builder()
                .memberId(review.getMember().getId())
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static <T> ReviewResponseDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResponseDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
