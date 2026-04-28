package com.example.umt10th.domain.mission.exception.code;

import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,"MISSION200_1", "미션이 성공적으로 조회되었습니다."),
    OK_2(HttpStatus.OK, "MISSION200_1", "미션이 성공 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 성공적으로 생성되었습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
