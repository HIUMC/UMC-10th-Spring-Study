package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.review.dto.ReviewReqDTO.Create;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(Member member, Restaurant restaurant, Create dto) {
        return Review.builder()
                .member(member)
                .restaurant(restaurant)
                .star(dto.star())
                .content(dto.content())
                .build();
    }

    public static ReviewResDTO.Info toInfo(Review review) {
        return ReviewResDTO.Info.builder()
                .id(review.getId())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }
}
