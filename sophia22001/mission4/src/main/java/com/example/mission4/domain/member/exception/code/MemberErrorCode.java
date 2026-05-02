package com.example.mission4.domain.member.exception.code;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 조회 실패
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),

    // 회원가입 실패 관련
    MEMBER_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER409_1", "이미 존재하는 회원입니다."),

    // 필수 값 누락
    MEMBER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "MEMBER400_1", "회원가입 정보가 올바르지 않습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

}
