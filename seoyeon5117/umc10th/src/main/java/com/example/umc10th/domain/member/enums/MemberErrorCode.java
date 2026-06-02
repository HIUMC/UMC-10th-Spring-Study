package com.example.umc10th.domain.member.enums;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "유저를 찾을 수 없습니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "이메일 또는 비밀번호가 올바르지 않습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.NOT_FOUND, "MEMBER409_", "이미 존재하는 유저입니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "MEMBER400_1", "지원하지 않는 소셜 로그인입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
