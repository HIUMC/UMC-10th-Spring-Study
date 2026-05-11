package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewImageRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final ReviewImageRepository reviewImageRepository;

    // 리뷰 작성
    public ReviewResDTO.WriteResult write(Long memberId, Long storeId, ReviewReqDTO.Write dto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        Review review = ReviewConverter.toReview(dto, member, store);

        reviewRepository.save(review);

        List<ReviewImage> images =
                ReviewConverter.toReviewImages(dto.imgUrls(), review);

        reviewImageRepository.saveAll(images);

        return ReviewConverter.write(review);

    }

    // 나의 리뷰 조회
    public ReviewResDTO.Pagination<ReviewResDTO.getMyReviews> getMyMissions(Long memberId, Integer pageSize, String cursor, String query, String sort) {

        Sort sortInfo;

        switch (sort) {

            case "LATEST":
            default:
                sortInfo = Sort.by("created_at").descending();
                break;

            case "OLDEST":
                sortInfo = Sort.by("created_at").ascending();
                break;

            case "RATING":
                sortInfo = Sort.by("star_rating").descending();
                break;

            case "ID":
                sortInfo = Sort.by("id").descending();
                break;
        }

        PageRequest pageRequest = PageRequest.of(0, pageSize, sortInfo);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        if (!cursor.equals("-1")) {

            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":

                    Long prevCursor =Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    reviewList = reviewRepository.getMyReviews_IdAndIdLessThanOrderByIdDesc(memberId, idCursor, pageRequest);
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.BAD_REQUEST);
            }

        } else {
            reviewList = reviewRepository.getMyReviews_IdOrderByIdDesc(memberId, pageRequest);
        }

        if (reviewList.getContent().isEmpty()) {
            return ReviewConverter.toPagination(
                    List.of(),
                    false,
                    null,
                    0
            );
        }

        nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toMyReviews).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
