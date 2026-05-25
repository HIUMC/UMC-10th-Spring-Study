package com.example.week4.domain.review.cursor;

public class ReviewCursorParser {

    private ReviewCursorParser() {

    }

    public static ReviewCursor parse(String cursor) {
        String[] parts = cursor.split(":");

        String sortType = parts[0];

        if ("ID".equals(sortType)) {
            return ReviewCursor.builder()
                    .sortType(sortType)
                    .cursorId(Long.parseLong(parts[1]))
                    .cursorRating(null)
                    .build();
        }

        if ("RATING".equals(sortType)) {
            return ReviewCursor.builder()
                    .sortType(sortType)
                    .cursorRating(Integer.parseInt(parts[1]))
                    .cursorId(Long.parseLong(parts[2]))
                    .build();
        }

        throw new IllegalArgumentException("지원하지 않는 정렬 기준입니다.");
    }
}
