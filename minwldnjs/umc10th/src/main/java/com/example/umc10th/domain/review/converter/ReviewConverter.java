package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewDetailDTO toReviewDetailDTO(Review review) {
        return ReviewResDTO.ReviewDetailDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }

    public static ReviewResDTO.ReviewSliceDTO toReviewSliceDTO(Slice<Review> slice, String nextCursor) {
        List<ReviewResDTO.ReviewDetailDTO> reviewList = slice.stream()
                .map(ReviewConverter::toReviewDetailDTO)
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewSliceDTO.builder()
                .reviewList(reviewList)
                .hasNext(slice.hasNext())
                .nextCursor(nextCursor)
                .size(slice.getNumberOfElements())
                .build();
    }
}