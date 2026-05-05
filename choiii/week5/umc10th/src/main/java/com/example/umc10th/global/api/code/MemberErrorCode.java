package com.example.umc10th.global.api.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}