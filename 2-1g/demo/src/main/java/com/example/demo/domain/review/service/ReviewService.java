package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewRequestDTO;
import com.example.demo.domain.review.dto.ReviewResponseDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.repository.StoreRepository;
import global.apiPayload.exception.GeneralException;
import global.code.GeneralErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResponseDTO.CreateReviewResultDTO createReview(
            Long memberId,
            ReviewRequestDTO.CreateReviewRequest request
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(savedReview);
    }
}
