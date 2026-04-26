package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "REVIEW401_1",
            "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "REVIEW403_1",
            "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰를 찾을 수 없습니다"
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
