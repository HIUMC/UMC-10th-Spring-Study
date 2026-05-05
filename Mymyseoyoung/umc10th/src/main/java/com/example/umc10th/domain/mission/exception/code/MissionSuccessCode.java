package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode

{MISSION_FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록 조회 성공"),
    MISSION_DETAIL_FOUND(HttpStatus.OK, "MISSION200_2", "미션 단건 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
