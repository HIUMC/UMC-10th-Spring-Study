package com.example.mission4.domain.mission.exception.code;

import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    // 조회 성공
    MISSIONS_FOUND(HttpStatus.OK, "MISSION200_1","성공적으로 미션을 조회했습니다."),

    // 미션 성공처리 성공
    MISSION_COMPLETED(HttpStatus.OK, "MISSION201_1", "성공적으로 미션 성공 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


}
