package com.example.umc10th.domain.memberterm.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberTermSuccessCode implements BaseSuccessCode {

    MEMBER_TERM_AGREE(HttpStatus.CREATED, "MTERM201", "약관 동의가 성공적으로 처리되었습니다."),
    MEMBER_TERM_GET(HttpStatus.OK, "MTERM200_1", "약관 목록을 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
