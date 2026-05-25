package com.example.week4.domain.user.exception.code;

import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    // 마이페이지 조회
    GET_MY_PAGE(HttpStatus.OK, "USER200_1", "마이페이지 조회에 성공했습니다."),
    // 마이페이지 수정
    UPDATE_MY_PAGE(HttpStatus.OK, "USER200_2", "마이페이지 수정에 성공했습니다."),
    // 회원가입
    SIGN_UP(HttpStatus.CREATED, "USER201_1", "회원가입에 성공했습니다."),
    // 로그인
    LOGIN(HttpStatus.OK, "USER200_3", "로그인에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
