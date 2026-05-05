package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.repository.StoreRepository;
import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO createReview(ReviewReqDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Review review = Review.builder()
                .content(request.getContent())
                .star(request.getStar())
                .member(member)
                .store(store)
                .build();

        Review saved = reviewRepository.save(review);

        return ReviewResDTO.builder()
                .reviewId(saved.getId())
                .content(saved.getContent())
                .star(saved.getStar())
                .createdAt(saved.getCreatedAt())
                .build();
    }
}
