package com.example.mission4.domain.review.converter;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.entity.Review;
import com.example.mission4.domain.store.entity.Store;

import java.util.List;

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

    public static ReviewResDTO.GetMyReview toGetMyReview(Review review, Member member){
        return ReviewResDTO.GetMyReview.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getName())
                        .username(member.getName())
                        .star(review.getStar())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt())
                        .build();

    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
