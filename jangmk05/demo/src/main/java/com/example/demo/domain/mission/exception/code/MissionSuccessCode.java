package com.example.demo.domain.mission.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    MISSION_FOUND(HttpStatus.OK, "MISSION2001", "미션 조회에 성공했습니다."),
    MISSION_CREATED(HttpStatus.CREATED, "MISSION2011", "미션이 성공적으로 생성되었습니다."),
    MISSION_UPDATED(HttpStatus.OK, "MISSION2002", "미션이 성공적으로 수정되었습니다."),
    MISSION_CHALLENGED(HttpStatus.OK, "MISSION2003", "미션 도전에 성공했습니다."),
    MISSION_COMPLETED(HttpStatus.OK, "MISSION2004", "미션 완료 처리에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
