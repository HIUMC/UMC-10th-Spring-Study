package com.example.week4.domain.review.converter;

import com.example.week4.domain.review.dto.ReviewReqDTO;
import com.example.week4.domain.review.dto.ReviewResDTO;
import com.example.week4.domain.review.entity.Review;
import com.example.week4.domain.store.entity.Store;
import com.example.week4.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(
            ReviewReqDTO.CreateReviewRequest dto,
            User user,
            Store store
    ) {
        return Review.builder()
                .user(user)
                .store(store)
                .rating(dto.rating())
                .reviewContent(dto.reviewContent())
                .reviewCreatedAt(LocalDateTime.now())
                .build();
    }

    public static ReviewResDTO.ReviewResponse toReviewResponse(Review review) {
        return ReviewResDTO.ReviewResponse.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .reviewContent(review.getReviewContent())
                .reviewCreatedAt(review.getReviewCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewListResponse toReviewListResponse(List<Review> reviews) {
        List<ReviewResDTO.ReviewResponse> reviewResponses = reviews.stream()
                .map(ReviewConverter::toReviewResponse)
                .toList();

        return ReviewResDTO.ReviewListResponse.builder()
                .reviews(reviewResponses)
                .build();
    }

    public static ReviewResDTO.UserReviewCursorListResponse toUserReviewCursorListResponse(
            List<Review> reviews,
            Integer pageSize,
            String sortType
    ) {
        boolean hasNext = reviews.size() > pageSize;

        List<Review> content = hasNext
                ? reviews.subList(0, pageSize)
                : reviews;

        String nextCursor = null;

        if (hasNext && !content.isEmpty()) {
            Review lastReview = content.get(content.size() - 1);

            if (sortType.equals("ID")) {
                nextCursor = "ID:" + lastReview.getId();
            }

            if (sortType.equals("RATING")) {
                nextCursor = "RATING:" + lastReview.getRating() + ":" + lastReview.getId();
            }
        }

        List<ReviewResDTO.ReviewResponse> reviewResponses = content.stream()
                .map(ReviewConverter::toReviewResponse)
                .toList();

        return ReviewResDTO.UserReviewCursorListResponse.builder()
                .reviews(reviewResponses)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .currentCount(content.size())
                .build();
    }
}