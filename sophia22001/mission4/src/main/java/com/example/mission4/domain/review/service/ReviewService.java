package com.example.mission4.domain.review.service;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import com.example.mission4.domain.review.converter.ReviewConverter;
import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.entity.Review;
import com.example.mission4.domain.review.repository.ReviewRepository;
import com.example.mission4.domain.store.entity.Store;
import com.example.mission4.domain.store.exception.StoreException;
import com.example.mission4.domain.store.exception.code.StoreErrorCode;
import com.example.mission4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.MyPageReview myPageReview(Long memberId, Long storeId, ReviewReqDTO.MyPageReview dto) {

        // 유저 조회
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 가게 조회
        Store store = storeRepository.findById(storeId).orElseThrow(()-> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 그 유저의 리뷰 작성
        Review review = ReviewConverter.toMyPageReview(member, store, dto);

        // 리뷰 저장
        reviewRepository.save(review);

        return ReviewResDTO.MyPageReview.builder()
                .reviewId(review.getId()).build();
    }
}
