package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    // 리뷰 생성
    @Transactional
    public ReviewResDTO.CreateReviewResultDTO createReview(
            Long storeId,
            ReviewReqDTO.CreateReviewRequest dto
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Member member = memberRepository.findByIdAndDeletedAtIsNull(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toReview(dto, member, store);
        reviewRepository.save(review);

        return ReviewConverter.toCreateReviewResultDTO(review);
    }

    // 내가 쓴 리뷰 조회
    public ReviewResDTO.Pagination<ReviewResDTO.GetReviewDTO> getReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String sort) {

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Double starCursor;
        String nextCursor = "";
        Slice<Review> reviewSlice;


        // 커서가 있는 경우
        if(!cursor.equals("-1")){
            switch(sort){
                case "id":
                    idCursor = Long.parseLong(cursor);
                    reviewSlice = reviewRepository.findReviewsByMember_IdAndIdLessThanOrderByIdDesc(
                            memberId, idCursor, pageRequest
                    );
                    break;

                case "star":
                    String[] split = cursor.split(":");
                    starCursor = Double.parseDouble(split[0]);
                    idCursor = Long.parseLong(split[1]);
                    reviewSlice = reviewRepository.findReviewsByStar(memberId, idCursor, starCursor, pageRequest);
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else { // 커서가 없는 경우
            switch(sort){
                case "id":
                    reviewSlice = reviewRepository.findReviewsByMember_IdOrderByIdDesc(memberId, pageRequest);
                    break;
                case "star":
                    reviewSlice = reviewRepository.findReviewsByMember_IdOrderByStarDesc(memberId, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        List<Review> reviews = reviewSlice.getContent();

        if (!reviews.isEmpty() && reviewSlice.hasNext()) {
            Review lastReview = reviews.get(reviews.size() - 1);

            if (sort.equals("id")) {
                nextCursor = String.valueOf(lastReview.getId());
            } else if (sort.equals("star")) {
                nextCursor = lastReview.getStar() + ":" + lastReview.getId();
            }
        }

//        nextCursor = reviewSlice.getContent().getLast().getStar() +
//                ":" + reviewSlice.getContent().getLast().getId();


        return ReviewConverter.toPagination(
                reviewSlice.map(ReviewConverter::toGetReviewDTO).toList(),
                reviewSlice.hasNext(),
                nextCursor,
                pageSize
        );
    }
}

