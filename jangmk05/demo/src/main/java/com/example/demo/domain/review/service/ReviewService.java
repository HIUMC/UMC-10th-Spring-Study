package com.example.demo.domain.review.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.exception.MemberException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.entity.Store;
import com.example.demo.domain.mission.exception.MissionException;
import com.example.demo.domain.mission.exception.code.MissionErrorCode;
import com.example.demo.domain.mission.exception.code.StoreErrorCode;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.domain.mission.repository.StoreRepository;
import com.example.demo.domain.review.converter.ReviewConverter;
import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.review.exception.ReviewException;
import com.example.demo.domain.review.exception.code.ReviewErrorCode;
import com.example.demo.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
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

    public ReviewResDTO.CreateReviewResultDTO createReview( ReviewReqDTO.CreateReviewDTO request) {
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (reviewRepository.existsByMemberAndMission(member, mission)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review review = ReviewConverter.toReview(request, member, mission);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(savedReview);


    }

    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> getStoreReviews(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor;

        if (!cursor.equals("-1")) {
            String[] split = cursor.split(":");

            switch (query.toLowerCase()) {
                case "id":
                    long idCursor = Long.parseLong(split[0]);

                    reviewList = reviewRepository
                            .findByMission_Store_IdAndIdLessThanOrderByIdDesc(
                                    storeId,
                                    idCursor,
                                    pageRequest
                            );
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.REVIEW_CURSOR_INVALID);
            }
        } else {
            reviewList = reviewRepository
                    .findByMission_Store_IdOrderByIdDesc(storeId, pageRequest);
        }

        if (reviewList.isEmpty()) {
            return ReviewConverter.toPagination(
                    store.getId(),
                    store.getName(),
                    reviewList.map(ReviewConverter::toGetReview).toList(),
                    false,
                    "-1",
                    0
            );
        }

        nextCursor = String.valueOf(
                reviewList.getContent().get(reviewList.getContent().size() - 1).getId()
        );

        return ReviewConverter.toPagination(
                store.getId(),
                store.getName(),
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getNumberOfElements()
        );
    }
}
