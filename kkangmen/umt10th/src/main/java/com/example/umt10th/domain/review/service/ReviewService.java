package com.example.umt10th.domain.review.service;

import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.exception.MemberException;
import com.example.umt10th.domain.member.exception.code.MemberErrorCode;
import com.example.umt10th.domain.member.repository.MemberRepository;
import com.example.umt10th.domain.mission.converter.MissionConverter;
import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.entity.Store;
import com.example.umt10th.domain.mission.exception.StoreException;
import com.example.umt10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umt10th.domain.mission.repository.StoreRepository;
import com.example.umt10th.domain.review.converter.ReviewConverter;
import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.dto.ReviewResDTO;
import com.example.umt10th.domain.review.entity.Review;
import com.example.umt10th.domain.review.exception.ReviewException;
import com.example.umt10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umt10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    /***
     * 리뷰 생성
     * @param dto
     * @param storeId
     * @param memberId
     * @return
     */
    public ReviewResDTO.CreateReviewResultDto createReview(ReviewReqDTO.CreateReviewDto dto, Long storeId, Long memberId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.buildReview(dto, store, member);
        reviewRepository.save(review);

        return new ReviewResDTO.CreateReviewResultDto(LocalDateTime.now());
    }

    /***
     * 나의 리뷰 전체 조회
     * @param dto
     * @param pageSize
     * @param cursor
     * @param sortBy
     * @return
     */
    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> getReviews(
            ReviewReqDTO.MyReview dto,
            Integer pageSize,
            String cursor,
            String sortBy
    ){

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        float ratingCursor;
        String nextCursor = "";
        Slice<Review> reviews;

        if (!cursor.equals("-1")){
            switch(sortBy){
                case "LATEST":
                    idCursor = Long.parseLong(cursor);
                    reviews = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(dto.memberId(), idCursor, pageRequest);
                    break;
                case "RATING":
                    String[] splitCursor = cursor.split(":");
                    ratingCursor = Float.parseFloat(splitCursor[0]);
                    idCursor = Long.parseLong(splitCursor[1]);
                    reviews = reviewRepository.findReviewsByRating(dto.memberId(), idCursor, ratingCursor, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else { // 커서가 없는 경우,
            switch(sortBy){
                case "LATEST":
                    reviews = reviewRepository.findReviewsByMember_IdOrderByIdDesc(dto.memberId(), pageRequest);
                    break;
                case "RATING":
                    reviews = reviewRepository.findReviewsByMember_IdOrderByRatingDescIdDesc(dto.memberId(), pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        nextCursor = reviews.getContent().getLast().getRating() + ":"
                + reviews.getContent().getLast().getId();


        return ReviewConverter.toPagination(
                reviews.map(ReviewConverter::toGetReview).toList(),
                reviews.hasNext(),
                nextCursor,
                pageSize
        );
    }
}
