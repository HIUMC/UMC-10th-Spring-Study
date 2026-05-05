package com.example.umc10th.domain.member.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_GET(HttpStatus.OK, "MEMBER200_1", "성공적으로 유저를 조회했습니다."),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER200_2", "성공적으로 유저를 수정했습니다."),
    MEMBER_GET_POINT(HttpStatus.OK, "MEMBER200_3", "성공적으로 포인트를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
