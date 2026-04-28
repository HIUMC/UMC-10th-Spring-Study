package com.example.umt10th.domain.review.service;

import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.dto.ReviewResDTO;
import com.example.umt10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private ReviewRepository repository;

    public ReviewResDTO.CreateReviewResultDto saveReview(ReviewReqDTO.CreateReviewDto dto) {

        LocalDateTime now = LocalDateTime.now();
        return new ReviewResDTO.CreateReviewResultDto(now);
    }
}
