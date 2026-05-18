package com.example.week4.domain.review.service;

import com.example.week4.domain.review.converter.ReviewConverter;
import com.example.week4.domain.review.dto.ReviewReqDTO;
import com.example.week4.domain.review.dto.ReviewResDTO;
import com.example.week4.domain.review.entity.Review;
import com.example.week4.domain.review.repository.ReviewRepository;
import com.example.week4.domain.store.entity.Store;
import com.example.week4.domain.store.repository.StoreRepository;
import com.example.week4.domain.user.entity.User;
import com.example.week4.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.week4.domain.review.cursor.ReviewCursor;
import com.example.week4.domain.review.cursor.ReviewCursorParser;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.ReviewResponse createReview(ReviewReqDTO.CreateReviewRequest dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Store store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Review review = ReviewConverter.toReview(dto, user, store);
        Review savedReview = reviewRepository.save(review);

        return ReviewConverter.toReviewResponse(savedReview);
    }

    public ReviewResDTO.UserReviewCursorListResponse getUserReviews(
            ReviewReqDTO.UserReviewListRequest dto,
            String cursor,
            Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(0, pageSize + 1);

        ReviewCursor reviewCursor = ReviewCursorParser.parse(cursor);

        List<Review> reviews;

        if (reviewCursor.isIdSort()) {
            reviews = reviewRepository.findUserReviewByIdCursor(
                    dto.userId(),
                    reviewCursor.getCursorId(),
                    pageRequest
            );
        } else if (reviewCursor.isRatingSort()) {
            reviews = reviewRepository.findUserReviewByRatingCursor(
                    dto.userId(),
                    reviewCursor.getCursorRating(),
                    reviewCursor.getCursorId(),
                    pageRequest
            );
        } else {
            throw new IllegalArgumentException("지원하지 않는 정렬 기준입니다.");
        }

        return ReviewConverter.toUserReviewCursorListResponse(
                reviews,
                pageSize,
                reviewCursor.getSortType()
        );
    }

    public ReviewResDTO.ReviewListResponse getStoreReviews(ReviewReqDTO.StoreReviewListRequest dto) {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Review> reviewPage = reviewRepository.findStoreReviews(
                dto.storeId(),
                pageRequest
        );

        return ReviewConverter.toReviewListResponse(reviewPage.getContent());
    }
}