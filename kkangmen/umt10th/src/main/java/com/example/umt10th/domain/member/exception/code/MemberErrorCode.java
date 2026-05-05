package com.example.umt10th.domain.member.exception.code;

import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_2",
            "회원을 찾을 수 없습니다."),
    MEMBER_BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
