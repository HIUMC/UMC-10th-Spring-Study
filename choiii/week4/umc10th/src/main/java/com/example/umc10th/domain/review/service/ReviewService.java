package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.request.ReviewCreateRequest;
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

    public List<ReviewResponse> getStoreReviews(Long storeId) {
        return reviewRepository.findAllByStoreId(storeId).stream()
                .map(review -> ReviewConverter.toResponse(
                        review,
                        reviewImageRepository.findAllByReviewId(review.getId()).stream()
                                .map(ReviewImage::getImageUrl)
                                .toList()))
                .toList();
    }
}
