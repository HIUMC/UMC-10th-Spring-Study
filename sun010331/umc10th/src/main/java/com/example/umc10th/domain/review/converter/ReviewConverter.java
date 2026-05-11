package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;

public class ReviewConverter {


    // 리뷰 작성
    public static Review toReview(
            ReviewReqDTO.storeReview dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .starRating(dto.star())
                .content(dto.content())
                .member(member)
                .store(store)
                .build();
    }


    public static ReviewResDTO.StoreReviewRes write(Review review){
        return ReviewResDTO.StoreReviewRes.builder()
                .id(review.getId())
                .build();
    }

}
