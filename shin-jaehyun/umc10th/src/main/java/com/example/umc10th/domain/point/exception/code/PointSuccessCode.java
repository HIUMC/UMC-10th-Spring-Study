package com.example.umc10th.domain.point.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PointSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 포인트를 획득했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
