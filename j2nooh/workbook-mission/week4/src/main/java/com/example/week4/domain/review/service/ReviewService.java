package com.example.week4.domain.review.service;

import com.example.week4.domain.review.converter.ReviewConverter;
import com.example.week4.domain.review.dto.ReviewReqDTO;
import com.example.week4.domain.review.dto.ReviewResDTO;
import com.example.week4.domain.review.entity.Review;
import com.example.week4.domain.review.repository.ReviewRepository;
import com.example.week4.domain.store.entity.Store;
import com.example.week4.domain.store.repository.StoreRepository;
import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.ReviewResponse createReview(ReviewReqDTO.CreateReviewRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = ReviewConverter.toReview(dto, user, store);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toReviewResponse(savedReview);
    }

    public ReviewResDTO.ReviewListResponse getUserReviews(ReviewReqDTO.UserReviewListRequest dto) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Review> reviewPage = reviewRepository.findUserReviews(
                dto.userId(),
                pageRequest
        );

        return ReviewConverter.toReviewListResponse(reviewPage.getContent());
    }

    public ReviewResDTO.ReviewListResponse getStoreReviews(ReviewReqDTO.StoreReviewListRequest dto) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Review> reviewPage = reviewRepository.findStoreReviews(
                dto.storeId(),
                pageRequest
        );

        return ReviewConverter.toReviewListResponse(reviewPage.getContent());
    }
}