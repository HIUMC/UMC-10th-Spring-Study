package umc10th.assignment.usermission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum UserMissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "USERMISSION200_1", "성공적으로 사용자 미션을 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
