package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "USER400_1",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "USER401_1",
            "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "USER403_1",
            "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "해당 유저를 찾을 수 없습니다"
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
