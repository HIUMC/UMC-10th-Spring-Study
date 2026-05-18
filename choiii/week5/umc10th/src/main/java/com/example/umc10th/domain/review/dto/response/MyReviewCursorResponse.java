package com.example.umc10th.domain.review.dto.response;

import java.util.List;

public record MyReviewCursorResponse(
        List<ReviewResponse> reviews,
        Long nextCursorId,
        Integer nextCursorRating,
        boolean hasNext
) {
}