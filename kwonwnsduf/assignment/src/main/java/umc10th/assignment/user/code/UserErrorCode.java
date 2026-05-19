package umc10th.assignment.user.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "해당 사용자를 찾을 수 없습니다."),

    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"USER400_1","이미 존재하는 이메일입니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
