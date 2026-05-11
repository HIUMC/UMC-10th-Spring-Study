package com.example.demo.domain.review.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(
            ReviewRequestDTO.CreateReviewRequest request,
            Member member,
            Store store
    ) {
        return new Review(
                null,
                store,
                member,
                request.getScore(),
                request.getContent(),
                new Date(),
                null
        );
    }

    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(toLocalDateTime(review.getCreateAt()))
                .build();
    }

    private static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static ReviewResponseDTO.MyReviewPreviewDTO toMyReviewPreviewDTO(Review review) {
        return ReviewResponseDTO.MyReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(toLocalDateTime(review.getCreateAt()))
                .build();
    }

    public static ReviewResponseDTO.MyReviewListResultDTO toMyReviewListResultDTO(
            List<Review> reviews,
            Long nextCursorId,
            Float nextCursorScore,
            Boolean hasNext
    ) {
        return ReviewResponseDTO.MyReviewListResultDTO.builder()
                .reviewList(reviews.stream()
                        .map(ReviewConverter::toMyReviewPreviewDTO)
                        .toList())
                .nextCursorId(nextCursorId)
                .nextCursorScore(nextCursorScore)
                .hasNext(hasNext)
                .build();
    }

}
