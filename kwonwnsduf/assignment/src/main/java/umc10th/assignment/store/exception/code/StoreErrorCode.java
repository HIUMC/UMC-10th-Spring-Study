package umc10th.assignment.store.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import tools.jackson.core.ObjectReadContext;
import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
@Getter
@RequiredArgsConstructor
public enum  StoreErrorCode implements BaseErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "STORE400_1",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "STORE401_1",
            "인증되지 않았습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "STORE403_1",
            "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다"
    ),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
