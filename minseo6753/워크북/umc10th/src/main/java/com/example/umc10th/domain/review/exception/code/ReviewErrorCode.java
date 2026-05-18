package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    INVALID_QUERY(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "쿼리값이 올바르지 않습니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾을 수 없습니다."),
    INVALID_CURSOR(HttpStatus.NOT_FOUND,
            "REVIEW404_2",
            "커서값이 잘못 되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
