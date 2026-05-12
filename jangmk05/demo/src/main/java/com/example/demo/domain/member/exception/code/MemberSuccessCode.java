package com.example.demo.domain.member.exception.code;

import com.example.demo.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_FOUND(HttpStatus.OK, "MEMBER2001", "회원 조회에 성공했습니다."),
    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER2011", "회원 가입에 성공했습니다."),
    MEMBER_UPDATED(HttpStatus.OK, "MEMBER2002", "회원 정보 수정에 성공했습니다."),
    MEMBER_DELETED(HttpStatus.OK, "MEMBER2003", "회원 탈퇴가 성공적으로 처리되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
