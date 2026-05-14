package com.example.mission4.domain.review.service;

import com.example.mission4.domain.member.entity.Member;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.repository.MemberRepository;
import com.example.mission4.domain.review.converter.ReviewConverter;
import com.example.mission4.domain.review.dto.ReviewReqDTO;
import com.example.mission4.domain.review.dto.ReviewResDTO;
import com.example.mission4.domain.review.entity.Review;
import com.example.mission4.domain.review.exception.ReviewException;
import com.example.mission4.domain.review.exception.code.ReviewErrorCode;
import com.example.mission4.domain.review.repository.ReviewRepository;
import com.example.mission4.domain.store.entity.Store;
import com.example.mission4.domain.store.exception.StoreException;
import com.example.mission4.domain.store.exception.code.StoreErrorCode;
import com.example.mission4.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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

    public ReviewResDTO.Pagination<ReviewResDTO.GetMyReview> getMyReview(Long memberId, Integer pageSize, String cursor, String query) {



        /**
         * 커서 기반 페이지네이션
         */
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        // 1. 유저 조회
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


        // 커서가 있는 경우
        if (!cursor.equals("-1")){
            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    // 커서 타입 반환
                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    /**
                     * 리뷰 조회 & where 절에 커서 값 기입
                     */
                        // 2. 그 유저의 리뷰 조회
                    reviewList = reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(memberId, idCursor, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }


        } else {
            // 커서 없이 조회
            reviewList = reviewRepository.findAllByMemberIdOrderByIdDesc(memberId, pageRequest);

        }

        // 다음 커서 계산
        nextCursor = reviewList.getContent().get(reviewList.getContent().size() - 1).getId() + ":" + reviewList.getContent().get(reviewList.getContent().size() - 1).getId();

        // 리뷰들 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(review->ReviewConverter.toGetMyReview(review, member)).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
