package umc10th.assignment.store.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;
@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
    GET_HOME(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 미션을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
