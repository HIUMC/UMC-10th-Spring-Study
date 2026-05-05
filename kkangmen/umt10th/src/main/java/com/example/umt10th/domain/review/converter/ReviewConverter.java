package com.example.umt10th.domain.review.converter;

import com.example.umt10th.domain.member.entity.Member;
import com.example.umt10th.domain.member.enums.Address;
import com.example.umt10th.domain.mission.entity.Store;
import com.example.umt10th.domain.review.dto.ReviewReqDTO;
import com.example.umt10th.domain.review.entity.Review;

import java.time.LocalDateTime;

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
}
