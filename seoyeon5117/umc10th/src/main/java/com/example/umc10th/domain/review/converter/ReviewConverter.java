package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return ReviewResDTO.GetReview.builder()
                .reviewId(review.getId())
                .memberNickname(review.getMember().getNickname())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .replyContent(review.getReply() != null ? review.getReply().getContent() : null)
                .replyCreatedAt(review.getReply() != null ? review.getReply().getCreatedAt() : null)
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
