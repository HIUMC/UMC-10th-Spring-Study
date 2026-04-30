package com.example.week4.domain.store.exception.code;

import com.example.week4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "존재하지 않는 가게입니다."),
    INVALID_STORE_REGION(HttpStatus.BAD_REQUEST, "STORE400_1", "가게 지역을 비어있을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
