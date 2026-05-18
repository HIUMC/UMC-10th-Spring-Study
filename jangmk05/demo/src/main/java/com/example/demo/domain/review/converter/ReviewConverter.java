package com.example.demo.domain.review.converter;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.review.dto.ReviewReqDTO;
import com.example.demo.domain.review.dto.ReviewResDTO;
import com.example.demo.domain.review.entity.Reply;
import com.example.demo.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateReviewDTO request, Member member, Mission mission) {
        return Review.builder()
                .member(member)
                .mission(mission)
                .rating(request.getRating())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .id(review.getId())
                .rating(review.getRating())
                .title(review.getTitle())
                .content(review.getContent())
                .build();
    }

    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .nickname(review.getMember().getName())
                .score(review.getRating())
                .content(review.getContent())
                .reply(review.getReply())
                .build();
    }

    public static ReviewResDTO.ReplyDTO toReplyDTO(Reply reply) {
        if (reply == null) return null;

        return ReviewResDTO.ReplyDTO.builder()
                .replyId(reply.getId())
                .content(reply.getContent())
                .nickname(reply.getMember().getName())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            Long storeId,
            String storeName,
            List<T> list,
            Boolean hasNext,
            String nextCursor,
            Integer listSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .storeId(storeId)
                .storeName(storeName)
                .list(list)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .listSize(listSize)
                .build();
    }
}
