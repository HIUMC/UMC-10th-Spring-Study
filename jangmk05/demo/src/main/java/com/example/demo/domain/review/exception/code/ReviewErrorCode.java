package com.example.demo.domain.review.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4001", "리뷰를 찾을 수 없습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW4002", "이미 해당 미션에 대한 리뷰를 작성했습니다."),
    REVIEW_FORBIDDEN(HttpStatus.FORBIDDEN, "REVIEW4003", "해당 리뷰에 접근할 권한이 없습니다."),
    REVIEW_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "REVIEW4004", "이미 삭제된 리뷰입니다."),
    INVALID_REVIEW_RATING(HttpStatus.BAD_REQUEST, "REVIEW4005", "별점은 1점 이상 5점 이하만 가능합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
