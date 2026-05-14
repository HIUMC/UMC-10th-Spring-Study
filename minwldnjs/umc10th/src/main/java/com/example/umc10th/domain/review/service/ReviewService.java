package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewResultDTO createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 가게입니다."));

        Review review = Review.builder()
                .content(request.getContent())
                .star(BigDecimal.valueOf(request.getScore()))
                .member(member)
                .store(store)
                .build();

        reviewRepository.save(review);
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .build();
    }

    // 커서 기반 페이지네이션으로 내 리뷰 조회
    @Transactional(readOnly = true)
    public ReviewResDTO.ReviewSliceDTO getMyReviews(Long memberId, String cursor, String sort, int size) {
        PageRequest pageRequest = PageRequest.of(0, size);
        Slice<Review> slice;
        String nextCursor = null;

        if (sort.equals("star")) {
            // 별점 순
            if (cursor == null || cursor.equals("-1")) {
                slice = reviewRepository.findByMemberIdOrderByStar(memberId, pageRequest);
            } else {
                String[] parts = cursor.split(":");
                BigDecimal starCursor = new BigDecimal(parts[0]);
                Long idCursor = Long.parseLong(parts[1]);
                slice = reviewRepository.findByMemberIdOrderByStar(memberId, starCursor, idCursor, pageRequest);
            }
            // 다음 커서 계산 (별점:ID)
            if (slice.hasNext()) {
                Review last = slice.getContent().get(slice.getContent().size() - 1);
                nextCursor = last.getStar() + ":" + last.getId();
            }
        } else {
            // ID 순 (기본)
            if (cursor == null || cursor.equals("-1")) {
                slice = reviewRepository.findByMemberIdOrderById(memberId, pageRequest);
            } else {
                String[] parts = cursor.split(":");
                Long idCursor = Long.parseLong(parts[1]);
                slice = reviewRepository.findByMemberIdOrderById(memberId, idCursor, pageRequest);
            }
            // 다음 커서 계산 (ID:ID)
            if (slice.hasNext()) {
                Review last = slice.getContent().get(slice.getContent().size() - 1);
                nextCursor = "id:" + last.getId();
            }
        }

        return ReviewConverter.toReviewSliceDTO(slice, nextCursor);
    }
}