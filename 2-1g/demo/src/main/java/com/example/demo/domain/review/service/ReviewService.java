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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public ReviewResponseDTO.MyReviewListResultDTO getMyReviews(
            Long memberId,
            Long cursor,
            Integer size
    ) {
        // pageSize 기본값은 10
        int pageSize = size == null || size < 1 ? 10 : size;

        org.springframework.data.domain.Pageable pageable = PageRequest.of(0, pageSize + 1);

        List<Review> reviews = cursor == null
                ? reviewRepository.findAllByMemberIdOrderByIdDesc(memberId, pageable)
                : reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(memberId, cursor, pageable);

        boolean hasNext = reviews.size() > pageSize;

        if (hasNext) {
            reviews = reviews.subList(0, pageSize);
        }

        Long nextCursor = reviews.isEmpty()
                ? null
                : reviews.get(reviews.size() - 1).getId();

        return ReviewConverter.toMyReviewListResultDTO(reviews, nextCursor, hasNext);
    }
}
