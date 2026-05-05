package com.example.umc10th.domain.mission.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_CREATE(HttpStatus.CREATED, "MISSION201", "미션이 성공적으로 등록되었습니다."),
    MISSION_GET(HttpStatus.OK, "MISSION200_1", "미션을 성공적으로 조회했습니다."),
    MISSIONS_GET(HttpStatus.OK, "MISSION200_2", "미션 목록을 성공적으로 조회했습니다."),
    MISSION_UPDATE(HttpStatus.OK, "MISSION200_3", "미션이 성공적으로 수정되었습니다."),
    MISSION_DELETE(HttpStatus.OK, "MISSION200_4", "미션이 성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
