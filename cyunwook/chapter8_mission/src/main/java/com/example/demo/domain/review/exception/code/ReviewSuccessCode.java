package com.example.demo.domain.review.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "REVIEW200_0", "리뷰 조회 성공"),
    CREATED(HttpStatus.CREATED, "REVIEW201_0", "리뷰 작성 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}