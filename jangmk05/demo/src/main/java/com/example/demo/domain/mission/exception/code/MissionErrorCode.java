package com.example.demo.domain.mission.exception.code;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "미션을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.CONFLICT, "MISSION4002", "이미 도전한 미션입니다."),
    MISSION_NOT_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION4003", "도전하지 않은 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "MISSION4004", "이미 완료한 미션입니다."),
    MISSION_NOT_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4005", "아직 완료되지 않은 미션입니다."),
    MISSION_REWARD_ALREADY_RECEIVED(HttpStatus.CONFLICT, "MISSION4006", "이미 보상을 수령한 미션입니다."),
    MISSION_FORBIDDEN(HttpStatus.FORBIDDEN, "MISSION4007", "해당 미션에 접근할 권한이 없습니다."),
    MISSION_STATUS_INVALID(HttpStatus.BAD_REQUEST, "MISSION4008", "현재 미션 상태에서는 처리할 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
