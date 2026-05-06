package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(Member member, Store store, ReviewReqDTO.ReviewWriteReqDTO request) {
        return Review.builder()
                .member(member)
                .store(store)
                .star(request.getStar())
                .content(request.getReviewContent())
                .build();
    }

    public static ReviewResDTO.ReviewWriteResDTO toReviewWriteResDTO(Review review) {
        return ReviewResDTO.ReviewWriteResDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .star(review.getStar())
                .reviewContent(review.getContent())
                .photoUrl(null)
                .build();
    }
}
