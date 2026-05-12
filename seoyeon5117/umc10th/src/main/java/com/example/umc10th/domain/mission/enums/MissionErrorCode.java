package com.example.umc10th.domain.mission.enums;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "미션을 찾을 수 없습니다."),
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST, "MISSION400_1", "유효하지 않은 조회 조건입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
