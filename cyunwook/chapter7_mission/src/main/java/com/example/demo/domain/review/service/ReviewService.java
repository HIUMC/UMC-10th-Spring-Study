package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.repository.StoreRepository;
import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ReviewResDTO.ReviewCursorDTO getMyReviews(
            Long memberId, Long cursor, int size, String sort) {

        // cursor 없으면 처음 조회 (Long.MAX_VALUE로 전체 조회)
        if (cursor == null) cursor = Long.MAX_VALUE;

        // size+1개 조회해서 다음 페이지 존재 여부 확인
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
