package com.example.week4.domain.review.service;

import com.example.week4.domain.review.dto.ReviewReqDTO;
import com.example.week4.domain.review.dto.ReviewResDTO;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    public ReviewResDTO.ReviewResponse createReview(ReviewReqDTO.CreateReviewRequest dto) {
        return null;
    }

    public ReviewResDTO.ReviewListResponse getUserReviews(ReviewReqDTO.UserReviewListRequest dto) {
        return null;
    }

    public ReviewResDTO.ReviewListResponse getStoreReviews(ReviewReqDTO.StoreReviewListRequest dto) {
        return null;
    }
}
