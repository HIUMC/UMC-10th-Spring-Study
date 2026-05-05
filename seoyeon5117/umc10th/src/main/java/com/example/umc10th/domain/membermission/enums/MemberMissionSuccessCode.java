package com.example.umc10th.domain.membermission.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberMissionSuccessCode implements BaseSuccessCode {

    MEMBER_MISSION_GET(HttpStatus.OK, "MMISSION200_1", "멤버 미션 목록을 성공적으로 조회했습니다."),
    MEMBER_MISSION_COMPLETE(HttpStatus.OK, "MMISSION200_2", "미션을 성공적으로 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
