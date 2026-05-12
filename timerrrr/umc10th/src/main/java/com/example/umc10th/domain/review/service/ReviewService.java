package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.math.BigDecimal;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewImageRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.exception.code.StoreErrorCode;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
    @Transactional
    public void createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReview dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ProjectException(StoreErrorCode.NOT_FOUND));

        Review savedReview = reviewRepository.save(ReviewConverter.toReview(dto, member, store));

        if (dto.imageUrls() != null && !dto.imageUrls().isEmpty()) {
            List<ReviewImage> reviewImages = ReviewConverter.toReviewImageList(dto.imageUrls(), savedReview);
            reviewImageRepository.saveAll(reviewImages);
        }
    }

    // 내가 생성한 리뷰들 조회 - 커서 기반 페이지네이션
    public ReviewResDTO.Pagination<ReviewResDTO.MyReviewInfo> getMyReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new ProjectException(MemberErrorCode.NOT_FOUND));

        // 페이지 정보 생성
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우 (첫 페이지가 아님)
        if (!cursor.equals("-1")) {
            // 커서 분리
            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {
                case "id": {
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findByMember_IdAndIdLessThanOrderByIdDesc(
                            memberId, idCursor, pageRequest
                    );
                    break;
                }
                case "star": {
                    BigDecimal starCursor = new BigDecimal(cursorSplit[0]);
                    Long idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findMyReviewsByStarCursor(
                            memberId, starCursor, idCursor, pageRequest
                    );
                    break;
                }
                default:
                    throw new ProjectException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 첫 페이지 (커서 없음)
            switch (query.toLowerCase()) {
                case "id":
                    reviewList = reviewRepository.findByMember_IdOrderByIdDesc(memberId, pageRequest);
                    break;
                case "star":
                    reviewList = reviewRepository.findByMember_IdOrderByStarDescIdDesc(memberId, pageRequest);
                    break;
                default:
                    throw new ProjectException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        // 다음 커서 계산
        if (reviewList.getContent().isEmpty()) {
            nextCursor = "-1";
        } else {
            Review last = reviewList.getContent().get(reviewList.getContent().size() - 1);
            switch (query.toLowerCase()) {
                case "id":
                    nextCursor = "0:" + last.getId();   //0:ID
                    break;
                case "star":
                    nextCursor = last.getStar() + ":" + last.getId();  //Stat:ID
                    break;
                default:
                    nextCursor = "-1";
            }
        }

        // 응답 DTO로 포장
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toMyReviewInfo).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
