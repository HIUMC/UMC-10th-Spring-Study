package com.example.umt10th.domain.mission.exception.code;

import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 Store를 찾을 수 없습니다.");
    ;

    private final HttpStatus status;
    private final String message;
    private final String code;
}
