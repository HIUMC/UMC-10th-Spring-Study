package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.ReviewWriteResDTO writeReview(Long storeId, ReviewReqDTO.ReviewWriteReqDTO request) {

        /*  요청으로 받은 이메일(userId)로 DB에서 회원을 조회, 없으면 MEMBER_NOT_FOUND 예외 */
        Member member = memberRepository.findByEmail(request.getUserId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        /* URL 경로에서 받은 storeId로 DB에서 가게를 조회 후 없으면 STORE_NOT_FOUND 예외 */
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        /* 조회한 member, store와 요청 데이터(rating, content)를 조합해서 Review 엔티티 객체 생성 */
        Review review = ReviewConverter.toReview(member, store, request);

        /* 만든 Review 객체를 DB에 저장 */
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toReviewWriteResDTO(saved);
    }
}
