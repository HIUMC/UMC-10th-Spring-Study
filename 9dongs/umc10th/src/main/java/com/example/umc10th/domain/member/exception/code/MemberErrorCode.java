package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "멤버를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"MEMBER401_1", "비밀번호가 틀립니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "이미 존재하는 이메일입니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "MEMBER400_1", "지원하지 않는 소셜 로그인 플랫폼입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
