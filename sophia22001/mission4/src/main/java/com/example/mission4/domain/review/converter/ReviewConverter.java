package com.example.mission4.domain.review.converter;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.entity.Review;
import com.example.mission4.domain.store.entity.Store;

public class ReviewConverter {
    public static Review toMyPageReview(Member member, Store store, ReviewReqDTO.MyPageReview dto) {
        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();

        return review;
    }
}
