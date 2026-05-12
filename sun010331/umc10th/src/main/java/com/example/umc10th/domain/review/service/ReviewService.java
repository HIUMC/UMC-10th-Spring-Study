package com.example.umc10th.domain.review.service;


import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.StoreException;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    //리뷰 작성
    public ReviewResDTO.StoreReviewRes createReviews(Long storeId, Long memberId, ReviewReqDTO.storeReview request) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Store store =  storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(request,member,store);

        reviewRepository.save(review);

        return ReviewConverter.write(review);
    }


    // 내가 생성한 리뷰들 조회하기
    public ReviewResDTO.ReviewCursorDTO getMyReviews(
            Long memberId,
            Long cursor,
            int size,
            String sort) {

        if( cursor == null) {
            cursor = Long.MAX_VALUE;
        }

        Pageable pageable = PageRequest.of(0, size + 1);
        List<Review> reviews;
        if ("star".equals(sort)) {
            reviews = reviewRepository.findByMemberIdOrderByStar(memberId, cursor, pageable);
        } else {
            reviews = reviewRepository.findByMemberIdOrderById(memberId, cursor, pageable);
        }

        return ReviewConverter.toCursorDTO(reviews, size);
    }
}
