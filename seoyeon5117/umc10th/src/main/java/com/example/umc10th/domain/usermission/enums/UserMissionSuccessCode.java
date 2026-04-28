package com.example.umc10th.domain.usermission.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserMissionSuccessCode implements BaseSuccessCode {

    USER_MISSION_GET(HttpStatus.OK, "UMISSION200_1", "유저 미션 목록을 성공적으로 조회했습니다."),
    USER_MISSION_COMPLETE(HttpStatus.OK, "UMISSION200_2", "미션을 성공적으로 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
