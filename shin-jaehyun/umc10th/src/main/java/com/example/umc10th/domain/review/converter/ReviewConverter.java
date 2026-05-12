package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {

    // 리뷰 작성
    public static Review toReview(
            ReviewReqDTO.Write dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .starRating(dto.starRating())
                .content(dto.content())
                .member(member)
                .store(store)
                .build();
    }

    public static List<ReviewImage> toReviewImages(
            List<String> imgUrls,
            Review review
    ) {
        return imgUrls.stream()
                .map(url -> ReviewImage.builder()
                        .imgUrl(url)
                        .review(review)
                        .build())
                .toList();
    }

    public static ReviewResDTO.WriteResult write(Review review) {
        return ReviewResDTO.WriteResult.builder()
                .id(review.getId())
                .build();
    }

    public static ReviewResDTO.getMyReviews toMyReviews(Review review) {

        List<String> imageUrls = review.getReviewImageList().stream()
                .map(ReviewImage::getImgUrl)
                .toList();

        return ReviewResDTO.getMyReviews.builder()
                .starRating(review.getStarRating())
                .content(review.getContent())
                .reviewImageList(imageUrls)
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(List<T> data, Boolean hasNext, String nextCursor, Integer pageSize) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
