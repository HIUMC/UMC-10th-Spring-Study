package com.example.demo.domain.review.dto;

import com.example.demo.domain.review.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateReviewResultDTO {
        private Long id;
        private Long rating;
        private String title;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetReview {
        private Long reviewId;
        private String nickname;
        private Long score;
        private String content;
        private Reply reply;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReplyDTO {
        private Long replyId;
        private String content;
        private String nickname;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination<T> {
        private Long storeId;
        private String storeName;
        private List<T> list;
        private Boolean hasNext;
        private String nextCursor;
        private Integer listSize;
    }
}
