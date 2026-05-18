package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .restaurantName(review.getRestaurant().getName())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPaginationDTO(List<T> data, Boolean hasNext, String nextCursor, Integer pageSize) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}