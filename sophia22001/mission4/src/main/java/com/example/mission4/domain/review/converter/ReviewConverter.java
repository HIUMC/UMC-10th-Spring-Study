package com.example.mission4.domain.review.converter;

import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.entity.Review;

public class ReviewConverter {
    public static Review toMyPageReview(ReviewReqDTO.MyPageReview dto) {
        Review review = Review.builder()
                .star(dto.star())
                .content(dto.content())
                .build();

        return review;
    }
}
