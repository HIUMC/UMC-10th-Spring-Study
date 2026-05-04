package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    MEMBER_JOIN(HttpStatus.OK, "MEMBER2001", "회원가입이 완료되었습니다."),
    MEMBER_LOGIN(HttpStatus.OK, "MEMBER2002", "로그인에 성공하였습니다."),
    MEMBER_FOUND(HttpStatus.OK, "MEMBER2003", "회원 정보 조회에 성공하였습니다."),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER2004", "회원 정보가 수정되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
