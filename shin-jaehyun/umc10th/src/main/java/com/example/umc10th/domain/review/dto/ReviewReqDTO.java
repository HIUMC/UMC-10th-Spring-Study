package com.example.umc10th.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record Write(
       BigDecimal starRating,
       String content,
       List<String> imgUrls
    ) {}
}
