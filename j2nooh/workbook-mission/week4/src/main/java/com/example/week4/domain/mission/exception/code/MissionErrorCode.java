package com.example.week4.domain.mission.exception.code;

import com.example.week4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "존재하지 않는 유저입니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_2", "존재하지 않는 가게입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_3", "존재하지 않는 미션입니다."),
    USER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_4", "존재하지 않는 유저 미션입니다."),

    INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION400_1", "올바르지 않은 미션 상태입니다."),
    MISSION_ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION400_2", "이미 도전중인 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
