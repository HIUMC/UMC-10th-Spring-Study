package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.request.ReviewCreateRequest;
import com.example.umc10th.domain.review.dto.response.ReviewResponse;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import java.util.List;

public class ReviewConverter {

    private ReviewConverter() {}

    public static Review toEntity(ReviewCreateRequest request, User user, Store store) {
        return Review.builder()
                .user(user)
                .store(store)
                .rating(request.rating())
                .reviewContent(request.reviewContent())
                .build();
    }

    public static ReviewImage toImageEntity(Review review, String imageUrl) {
        return ReviewImage.builder()
                .review(review)
                .imageUrl(imageUrl)
                .build();
    }

    public static ReviewResponse toResponse(Review review, List<String> imageUrls) {
        return new ReviewResponse(
                review.getId(), review.getUser().getId(), review.getUser().getName(),
                review.getStore().getId(), review.getStore().getStoreName(),
                review.getRating(), review.getReviewContent(), review.getCreatedAt()
        );
    }
}
