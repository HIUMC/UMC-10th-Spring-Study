package com.example.umc10th.domain.review.dto;

import com.example.umc10th.domain.review.enums.StarRating;

import java.util.List;

public class ReviewReqDTO {

    // 리뷰 작성
    public record Write(
       String storeName,
       StarRating starRating,
       String content,
       List<String> imgUrls
    ) {}
}
