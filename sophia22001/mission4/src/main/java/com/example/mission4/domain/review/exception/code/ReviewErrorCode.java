package com.example.mission4.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode {

    // 필수 값 누락
    REVIEW_BAD_REQUEST(HttpStatus.BAD_REQUEST, "REVIEW400_1", "리뷰 정보가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}