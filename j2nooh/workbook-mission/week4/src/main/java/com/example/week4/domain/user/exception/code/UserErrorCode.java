package com.example.week4.domain.user.exception.code;

import com.example.week4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    // 유저 조회 실패
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "존재하지 않는 유저입니다."),

    // 마이페이지 수정 시 이름 값 오류
    INVALID_USER_NAME(HttpStatus.BAD_REQUEST, "USER400_1", "유저 이름이 비어 있으면 안됩니다."),

    // 로그인 비밀번호 불일치
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "USER401_1", "비밀번호가 일치하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
