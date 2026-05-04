package com.example.umc10th.domain.store.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    // 가게 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4041", "해당 가게를 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4042", "존재하지 않는 지역입니다."),
    STORE_ALREADY_EXISTS(HttpStatus.CONFLICT, "STORE4091", "이미 등록된 가게입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}