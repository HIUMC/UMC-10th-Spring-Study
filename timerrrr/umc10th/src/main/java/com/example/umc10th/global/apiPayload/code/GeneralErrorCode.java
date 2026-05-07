package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    // 400
    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400_1",
            "잘못된 요청입니다."),

    // 401
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "COMMON401_1",
            "인증되지 않았습니다."),

    // 403
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON403_1",
            "접근이 금지되었습니다."),

    // 404
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404_1",
            "해당 리소스를 찾을 수 없습니다."),

    // 405
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,
            "COMMON405_1",
            "허용되지 않은 HTTP 메서드입니다."),

    // 409
    CONFLICT(HttpStatus.CONFLICT,
            "COMMON409_1",
            "이미 존재하는 리소스입니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500_1",
            "서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
