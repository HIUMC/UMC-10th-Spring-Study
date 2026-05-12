package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // ReviewReqDTO.CreateReview -> Review
    public static Review toReview(ReviewReqDTO.CreateReview dto, Member member, Store store) {
        return Review.builder()
                .star(dto.star())
                .ReviewDetail(dto.reviewDetail())
                .member(member)
                .store(store)
                .build();
    }

    // imageUrl -> ReviewImage
    public static ReviewImage toReviewImage(String imageUrl, Review review) {
        return ReviewImage.builder()
                .ReviewImageUrl(imageUrl)
                .review(review)
                .build();
    }

    // List<String> -> List<ReviewImage>
    public static List<ReviewImage> toReviewImageList(List<String> imageUrls, Review review) {
        return imageUrls.stream()
                .map(url -> toReviewImage(url, review))
                .collect(Collectors.toList());
    }

    // Review -> ReviewResDTO.MyReviewInfo
    public static ReviewResDTO.MyReviewInfo toMyReviewInfo(Review review) {
        return ReviewResDTO.MyReviewInfo.builder()
                .reviewId(review.getId())
                .star(review.getStar())
                .reviewDetail(review.getReviewDetail())
                .storeName(review.getStore().getName())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data, Boolean hasNext, String nextCursor, Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
