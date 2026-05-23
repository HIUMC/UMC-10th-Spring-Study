package com.example.mission4.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode {

    // 조회 실패
    MISSIONS_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1","미션을 찾을 수 없습니다."),

    // 미션 등록 실패 관련
    MISSION_COMPLETED(HttpStatus.CONFLICT, "MISSION409_1", "이미 존재하는 미션입니다."),

    // 필수 값 누락
    MISSION_BAD_REQUEST(HttpStatus.BAD_REQUEST, "MISSION400_1", "미션 정보가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
