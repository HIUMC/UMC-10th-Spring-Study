package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.enums.ReviewErrorCode;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public ReviewResDTO.CreateReview createReview(Long storeId, ReviewReqDTO.CreateReview dto) {
        return null;
    }

    public ReviewResDTO.GetReview getReview(Long reviewId) {
        return null;
    }

    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> getReviewByMemberId(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");
            switch (query.toLowerCase()) {
                case "id":
                    idCursor = Long.parseLong(cursorSplit[0]);
                    reviewList = reviewRepository.findByMemberIdAndIdCursor(memberId, idCursor, pageRequest);
                    break;
                case "star":
                    float starCursor = Float.parseFloat(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);
                    reviewList = reviewRepository.findByMemberIdAndStarCursor(memberId, starCursor, idCursor, pageRequest);
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        } else {
            switch (query.toLowerCase()) {
                case "star":
                    reviewList = reviewRepository.findByMemberIdOrderByStar(memberId, pageRequest);
                    break;
                default:
                    reviewList = reviewRepository.findByMemberId(memberId, pageRequest);
            }
        }

        Review last = reviewList.getContent().getLast();
        nextCursor = switch (query.toLowerCase()) {
            case "star" -> last.getStar() + ":" + last.getId();
            default -> String.valueOf(last.getId());
        };

        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }

    public ReviewResDTO.UpdateReview updateReview(Long reviewId, ReviewReqDTO.CreateReview dto) {
        return null;
    }

    public Page<ReviewResDTO.GetReview> getReviewByStoreId(Long storeId, Pageable pageable) {
        return reviewRepository.findByStoreId(storeId, pageable)
                .map(ReviewConverter::toGetReview);
    }
}
