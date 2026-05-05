package umc10th.assignment.usermission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
@Getter
@RequiredArgsConstructor
public enum UserMissionErrorCode implements BaseErrorCode {
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "USERMISSION404_1", "해당 사용자 미션을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
