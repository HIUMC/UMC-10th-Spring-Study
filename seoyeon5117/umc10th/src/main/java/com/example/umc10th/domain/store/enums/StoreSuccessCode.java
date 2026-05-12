package com.example.umc10th.domain.store.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_GET(HttpStatus.OK, "STORE200_1", "가게가 성공적으로 조회되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
