package com.example.mission4.domain.review.service;

import com.example.mission4.domain.review.converter.ReviewConverter;
import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.entity.Review;
import com.example.mission4.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResDTO.MyPageReview myPageReview(ReviewReqDTO.MyPageReview dto) {

        Review review = ReviewConverter.toMyPageReview(dto);
        reviewRepository.save(review);

        return ReviewResDTO.MyPageReview.builder()
                .reviewId(review.getId()).build();
    }
}
