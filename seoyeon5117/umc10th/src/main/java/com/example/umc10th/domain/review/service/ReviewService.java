package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public ReviewResDTO.CreateReview createReview(ReviewReqDTO.CreateReview dto) {
        return null;
    }

    public ReviewResDTO.GetReview getReview(Long reviewId) {
        return null;
    }

    public ReviewResDTO.UpdateReview updateReview(Long reviewId, ReviewReqDTO.CreateReview dto) {
        return null;
    }

    public Page<ReviewResDTO.GetReview> getReviewByStoreId(Long storeId, Pageable pageable) {
        return reviewRepository.findByStoreId(storeId, pageable)
                .map(ReviewConverter::toGetReview);
    }
}
