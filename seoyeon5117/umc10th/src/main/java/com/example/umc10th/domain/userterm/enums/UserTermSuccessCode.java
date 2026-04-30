package com.example.umc10th.domain.userterm.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserTermSuccessCode implements BaseSuccessCode {

    USER_TERM_AGREE(HttpStatus.CREATED, "UTERM201", "약관 동의가 성공적으로 처리되었습니다."),
    USER_TERM_GET(HttpStatus.OK, "UTERM200_1", "약관 목록을 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
