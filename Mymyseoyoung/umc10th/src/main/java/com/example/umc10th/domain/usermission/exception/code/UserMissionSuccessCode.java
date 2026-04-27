package com.example.umc10th.domain.usermission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum UserMissionSuccessCode implements BaseSuccessCode {

    MISSION_CHALLENGED(HttpStatus.CREATED, "UM201", "미션 도전을 시작했습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "UM200", "미션을 성공적으로 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
