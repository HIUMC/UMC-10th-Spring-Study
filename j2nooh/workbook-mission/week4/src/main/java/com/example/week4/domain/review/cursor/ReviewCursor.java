package com.example.week4.domain.review.cursor;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class ReviewCursor {

    private String sortType;
    private Long cursorId;
    private Integer cursorRating;

    public boolean isIdSort() {
        return "ID".equals(sortType);
    }

    public boolean isRatingSort() {
        return "RATING".equals(sortType);
    }
}
