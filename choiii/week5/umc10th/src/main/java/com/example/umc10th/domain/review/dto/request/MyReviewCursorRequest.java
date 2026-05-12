package com.example.umc10th.domain.review.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MyReviewCursorRequest(

        @NotNull(message = "사용자 ID는 필수입니다.")
        Long userId,

        Long cursorId,

        Integer cursorRating,

        @NotNull(message = "조회 개수는 필수입니다.")
        @Min(value = 1, message = "조회 개수는 1 이상이어야 합니다.")
        Integer size,

        @NotNull(message = "정렬 기준은 필수입니다.")
        ReviewSortType sortType
) {
}