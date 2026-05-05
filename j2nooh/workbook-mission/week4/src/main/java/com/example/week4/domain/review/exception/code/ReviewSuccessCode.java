package com.example.week4.domain.review.exception.code;

import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATE_REVIEW(HttpStatus.OK, "REVIEW200_1", "리뷰 작성에 성공했습니다."),
    GET_USER_REVIEWS(HttpStatus.OK, "REVIEW200_2", "유저 리뷰 목록 조회에 성공했습니다."),
    GET_STORE_REVIEWS(HttpStatus.OK, "REVIEW200_3", "가게 리뷰 목록 조회에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
