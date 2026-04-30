package com.example.umc10th.domain.term.enums;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermErrorCode implements BaseErrorCode {

    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "TERM404_1", "약관을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
