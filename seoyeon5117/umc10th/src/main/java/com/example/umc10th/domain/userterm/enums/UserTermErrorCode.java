package com.example.umc10th.domain.userterm.enums;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserTermErrorCode implements BaseErrorCode {

    USER_TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "UTERM404_1", "약관 내역을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
