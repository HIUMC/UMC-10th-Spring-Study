package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MissionSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 수행했습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
