package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(
            ReviewReqDTO.ReviewDetailDto dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .content(dto.content())
                .star(dto.rating())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
