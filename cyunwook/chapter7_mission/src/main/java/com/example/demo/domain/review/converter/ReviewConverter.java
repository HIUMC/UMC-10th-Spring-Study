package com.example.demo.domain.review.converter;

import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {
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

        // hasNext 확인용으로 size+1개 조회했으므로 실제 데이터는 size개만
        List<Review> content = hasNext ? reviews.subList(0, size) : reviews;

        Long nextCursor = content.isEmpty() ? null : content.get(content.size() - 1).getId();

        return ReviewResDTO.ReviewCursorDTO.builder()
                .reviews(content.stream().map(ReviewConverter::toGetReview).toList())
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
