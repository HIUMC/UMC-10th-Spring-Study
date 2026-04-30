package com.example.umc10th.domain.term.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TermSuccessCode implements BaseSuccessCode {

    TERM_CREATE(HttpStatus.CREATED, "TERM201", "약관이 성공적으로 등록되었습니다."),
    TERM_GET(HttpStatus.OK, "TERM200_1", "약관을 성공적으로 조회했습니다."),
    TERMS_GET(HttpStatus.OK, "TERM200_2", "약관을 성공적으로 조회했습니다."),
    TERM_UPDATE(HttpStatus.OK, "TERM200_3", "약관이 성공적으로 수정되었습니다."),
    TERM_DELETE(HttpStatus.OK, "TERM200_4", "약관이 성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
