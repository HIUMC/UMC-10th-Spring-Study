package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewRequestDTO;
import com.example.umc10th.domain.review.dto.ReviewResponseDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResponseDTO.CreateResultDTO createReview(Long storeId, ReviewRequestDTO.CreateReview request)

    {
        // Store 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ProjectException(StoreErrorCode.STORE_NOT_FOUND));

        //Member 정보 가져오기 ( 로그인 없는 버전 )
        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

        //DTO 변환
        Review newReview = Review.builder()
                .content(request.content())
                .rate(request.rate())
                .store(store)
                .member(member)
                .build();

        //저장
        Review savedReview = reviewRepository.save(newReview);

        //결과 DTO 반환
        return ReviewConverter.toCreateResultDTO(savedReview);
    }

    //내가 생성한 리뷰들 조회하기

    public ReviewResponseDTO.Pagination<ReviewResponseDTO.getMyReviewDTO> getMyReviews(
        Long memberId,
        String cursor,
        String sortType,
        Integer pageSize)
    {

        Pageable pageable = PageRequest.of(0,10);
        Page<Review> result;

        if ("rating".equals(sortType)) {
            Float ratingCursor = cursor != null ? Float.parseFloat(cursor) : Float.MAX_VALUE;
            result = reviewRepository.findByMemberIdAndRateLessThan(memberId, ratingCursor, pageable);
        } else {
            Long idCursor = cursor != null ? Long.parseLong(cursor) : 0L;
            result = reviewRepository.findByMemberIdAndIdGreaterThan(memberId, idCursor, pageable);
        }

        String nextCursor = result.hasNext()
                ? "rating".equals(sortType)
                ? String.valueOf(result.getContent().getLast().getRate())
                : String.valueOf(result.getContent().getLast().getId())
                : null;

        return ReviewConverter.toPagination(
                result.map(ReviewConverter::toGetMyReviewDTO).toList(),
                result.hasNext(),
                nextCursor,
                pageSize
        );
    }
}
