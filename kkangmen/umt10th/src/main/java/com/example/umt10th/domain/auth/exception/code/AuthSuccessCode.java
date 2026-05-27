package com.example.umt10th.domain.auth.exception.code;

import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "AUTH201_1", "회원이 성공적으로 등록되었습니다."),
    OK(HttpStatus.OK, "AUTH200_1", "회원이 성공적으로 조회되었습니다." )
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
