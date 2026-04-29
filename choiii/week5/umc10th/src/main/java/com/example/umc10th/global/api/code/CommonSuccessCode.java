package com.example.umc10th.global.api.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements BaseSuccessCode {

    OK(
            HttpStatus.OK,
            "COMMON200",
            "요청에 성공했습니다."
    ),

    CREATED(
            HttpStatus.CREATED,
            "COMMON201",
            "생성에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}