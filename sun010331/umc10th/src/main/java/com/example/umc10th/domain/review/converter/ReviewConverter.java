package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {


    // 리뷰 작성
    public static Review toReview(
            ReviewReqDTO.storeReview dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .star(dto.star())
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

    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewCursorDTO toCursorDTO(
            List<Review> reviews, int size) {

        boolean hasNext = reviews.size() > size;


        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return ReviewResDTO.ReviewCursorDTO.builder()
                .reviews(content.stream().map(ReviewConverter::toGetReview).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

}
