package umc10th.assignment.global.apiPayload.code.status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseErrorCode {
    OK(HttpStatus.OK, "COMMON200_1", "성공적으로 요청을 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
