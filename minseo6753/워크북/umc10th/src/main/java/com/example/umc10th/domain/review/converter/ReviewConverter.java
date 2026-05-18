package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.review.dto.ReviewReqDTO.Create;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import java.util.List;

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
                .memberNickname(review.getMember().getNickname())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static <T> ReviewResDTO.Slice<T> toSlice(
            List<T> data,
            Boolean hasNext,
            Long nexCursor,
            Integer pageSize
    ){
        return ReviewResDTO.Slice.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nexCursor)
                .pageSize(pageSize)
                .build();
    }
}
