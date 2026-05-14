package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    public Review createReview(Long memberId, Long restaurantId, ReviewReqDTO.CreateReviewDTO request) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow();

        Review review = Review.builder()
                .star(request.star())
                .content(request.content())
                .member(member)
                .restaurant(restaurant)
                .build();

        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public ReviewResDTO.Pagination<ReviewResDTO.MyReviewDTO> getMyReviews(Long memberId, String cursor, String query, Integer size) {
        Slice<Review> reviewSlice;
        PageRequest pageRequest = PageRequest.of(0, size);

        if (cursor == null || cursor.trim().isEmpty()) {
            if ("star".equalsIgnoreCase(query)) {
                reviewSlice = reviewRepository.findMyReviewsByStarOrder(memberId, pageRequest);
            } else {
                reviewSlice = reviewRepository.findMyReviewsByIdOrder(memberId, pageRequest);
            }
        } else {
            if ("star".equalsIgnoreCase(query)) {
                String[] parts = cursor.split(":");
                Float star = Float.parseFloat(parts[0]);
                Long reviewId = Long.parseLong(parts[1]);
                reviewSlice = reviewRepository.findMyReviewsByStarCursor(memberId, star, reviewId, pageRequest);
            } else {
                Long reviewId = Long.parseLong(cursor);
                reviewSlice = reviewRepository.findMyReviewsByIdCursor(memberId, reviewId, pageRequest);
            }
        }

        List<ReviewResDTO.MyReviewDTO> dtoList = reviewSlice.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .toList();

        String nextCursor = null;
        if (reviewSlice.hasNext() && !reviewSlice.getContent().isEmpty()) {
            Review lastReview = reviewSlice.getContent().get(reviewSlice.getContent().size() - 1);
            if ("star".equalsIgnoreCase(query)) {
                nextCursor = lastReview.getStar() + ":" + lastReview.getId();
            } else {
                nextCursor = String.valueOf(lastReview.getId());
            }
        }

        return ReviewConverter.toPaginationDTO(dtoList, reviewSlice.hasNext(), nextCursor, size);
    }
}