package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.request.MyReviewCursorRequest;
import com.example.umc10th.domain.review.dto.request.ReviewCreateRequest;
import com.example.umc10th.domain.review.dto.request.ReviewSortType;
import com.example.umc10th.domain.review.dto.response.MyReviewCursorResponse;
import com.example.umc10th.domain.review.dto.response.ReviewResponse;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.service.StoreService;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.service.UserService;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final com.example.umc10th.User.review.repository.ReviewImageRepository reviewImageRepository;
    private final UserService userService;
    private final StoreService storeService;

    @Transactional
    public ReviewResponse createReview(Long userId, ReviewCreateRequest request) {
        User user = userService.findUser(userId);
        Store store = storeService.findStore(request.storeId());
        Review review = reviewRepository.save(ReviewConverter.toEntity(request, user, store));

        List<String> imageUrls = request.imageUrls() == null ? Collections.emptyList() : request.imageUrls();
        for (String imageUrl : imageUrls) {
            reviewImageRepository.save(ReviewConverter.toImageEntity(review, imageUrl));
        }
        store.increaseReviewCount();

        return ReviewConverter.toResponse(review, imageUrls);
    }

    public List<ReviewResponse> getStoreReviews(Long storeId,  Pageable pageable) {
        return reviewRepository.findAllByStoreId(storeId, pageable)
                .map(review -> ReviewConverter.toResponse(
                        review,
                        reviewImageRepository.findAllByReviewId(review.getId()).stream()
                                .map(ReviewImage::getImageUrl)
                                .toList()))
                .toList();
    }

    public MyReviewCursorResponse getMyReviews(MyReviewCursorRequest request) {
        Pageable pageable = PageRequest.of(0, request.size() + 1);

        List<Review> reviews;

        if (request.sortType() == ReviewSortType.ID) {
            reviews = reviewRepository.findMyReviewsByIdCursor(
                    request.userId(),
                    request.cursorId(),
                    pageable
            );
        } else {
            reviews = reviewRepository.findMyReviewsByRatingCursor(
                    request.userId(),
                    request.cursorRating(),
                    request.cursorId(),
                    pageable
            );
        }

        boolean hasNext = reviews.size() > request.size();

        if (hasNext) {
            reviews = reviews.subList(0, request.size());
        }

        Long nextCursorId = null;
        Integer nextCursorRating = null;

        if (!reviews.isEmpty()) {
            Review lastReview = reviews.get(reviews.size() - 1);
            nextCursorId = lastReview.getId();
            nextCursorRating = lastReview.getRating();
        }

        List<ReviewResponse> responseList = reviews.stream()
                .map(review -> new ReviewResponse(
                        review.getId(),
                        review.getUser().getId(),
                        review.getUser().getName(),
                        review.getStore().getId(),
                        review.getStore().getStoreName(),
                        review.getRating(),
                        review.getReviewContent(),
                        review.getCreatedAt()
                ))
                .toList();

        return new MyReviewCursorResponse(
                responseList,
                nextCursorId,
                nextCursorRating,
                hasNext
        );
    }
}
