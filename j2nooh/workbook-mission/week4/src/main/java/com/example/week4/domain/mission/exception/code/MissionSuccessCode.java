package com.example.week4.domain.mission.exception.code;

import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    GET_STORE_MISSIONS(HttpStatus.OK, "MISSION200_1", "가게 미션 목록 조회에 성공했습니다."),
    GET_USER_MISSIONS(HttpStatus.OK, "MISSION200_2", "유저 미션 목록 조회에 성공했습니다."),
    CHALLENGE_MISSION(HttpStatus.OK, "MISSION200_3", "미션 도전에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
