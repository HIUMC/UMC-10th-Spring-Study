package com.example.mission4.domain.store.exception.code;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    // 필수 값 누락
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "가게를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}