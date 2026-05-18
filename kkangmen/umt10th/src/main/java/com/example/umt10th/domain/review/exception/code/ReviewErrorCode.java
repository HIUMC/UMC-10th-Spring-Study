package com.example.umt10th.domain.review.exception.code;

import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "미션을 찾을 수 없습니다."),
    QUERY_NOT_VALID(HttpStatus.NOT_ACCEPTABLE, "REVIEW406_1", "잘못된 쿼리입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
