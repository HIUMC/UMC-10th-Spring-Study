package com.example.umt10th.domain.mission.exception.code;

import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "미션을 찾을 수 없습니다."),
    MISSION_FORBIDDEN(HttpStatus.FORBIDDEN,
            "MISSION403_1",
            "미션 목록을 조회할 권한이 없습니다."),
    QUERY_NOT_VALID(HttpStatus.NOT_ACCEPTABLE, "MISSION406_1" , "잘못된 쿼리 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
