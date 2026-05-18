package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static Review toReview( ReviewReqDTO.CreateReviewRequest dto, Member member, Store store
    ) {
        return Review.builder()
                .content(dto.content())
                .star(dto.star())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.GetReviewDTO toGetReviewDTO(Review review) {
        return ReviewResDTO.GetReviewDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .star(review.getStar())
                .content(review.getContent())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(List<T> data, Boolean hasNext, String nextCursor, Integer pageSize) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

}
