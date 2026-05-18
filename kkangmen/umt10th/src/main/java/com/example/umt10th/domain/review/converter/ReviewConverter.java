package com.example.umt10th.domain.review.converter;

import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.entity.Store;
import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.dto.ReviewResDTO;
import com.example.umt10th.domain.review.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    public static Review buildReview(ReviewReqDTO.CreateReviewDto dto, Store store, Member member){

        return Review.builder()
                .content(dto.reviewDetailDto().content())
                .createdAt(LocalDateTime.now())
                .rating(dto.reviewDetailDto().rating())
                .store(store)
                .member(member)
                .build();
    }

    // 나의 리뷰 틀
    public static ReviewResDTO.GetReview toGetReview(Review review){
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .rating(review.getRating())
                .name(review.getMember().getName())
                .content(review.getContent())
                .memberId(review.getMember().getId())
                .build();
    }

    // 페이지네이션 틀
    public static <T>ReviewResDTO.Pagination<T> toPagination(List<T> data, Boolean hasNext, String nextCursor, Integer pageSize){
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
