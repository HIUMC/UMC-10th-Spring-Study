package com.example.demo.domain.member.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 에러 상태코드, 내가 정한 코드, 에러 메시지
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 회원을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}