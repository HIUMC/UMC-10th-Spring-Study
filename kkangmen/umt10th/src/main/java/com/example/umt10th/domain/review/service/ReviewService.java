package com.example.umt10th.domain.review.service;

import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.exception.MemberException;
import com.example.umt10th.domain.member.exception.code.MemberErrorCode;
import com.example.umt10th.domain.member.repository.MemberRepository;
import com.example.umt10th.domain.mission.entity.Store;
import com.example.umt10th.domain.mission.exception.StoreException;
import com.example.umt10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umt10th.domain.mission.repository.StoreRepository;
import com.example.umt10th.domain.review.converter.ReviewConverter;
import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.dto.ReviewResDTO;
import com.example.umt10th.domain.review.entity.Review;
import com.example.umt10th.domain.review.repository.ReviewPhotoRepository;
import com.example.umt10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public ReviewResDTO.CreateReviewResultDto createReview(ReviewReqDTO.CreateReviewDto dto, Long storeId, Long memberId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.buildReview(dto, store, member);
        reviewRepository.save(review);

        return new ReviewResDTO.CreateReviewResultDto(LocalDateTime.now());
    }
}
