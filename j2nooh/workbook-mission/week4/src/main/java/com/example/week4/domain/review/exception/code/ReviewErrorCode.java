package com.example.week4.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "존재하지 않는 리뷰입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_2", "존재하지 않는 유저입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_3", "존재하지 않는 가게입니다."),
    INVALID_RATING(HttpStatus.BAD_REQUEST, "REVIEW400_1", "평점은 1점 이상 5점 이하만 가능합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
