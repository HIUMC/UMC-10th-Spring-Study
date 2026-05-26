package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    // --- 회원 (MEMBER) 관련 성공 응답 ---

    // 200 OK
    MEMBER_LOGIN(HttpStatus.OK, "MEMBER2001", "로그인에 성공하였습니다."),
    MEMBER_FOUND(HttpStatus.OK, "MEMBER2002", "성공적으로 회원을 조회했습니다."),
    MEMBER_UPDATE(HttpStatus.OK, "MEMBER2003", "회원 정보가 수정되었습니다."),
    MEMBER_POINT_FOUND(HttpStatus.OK, "MEMBER2004", "성공적으로 포인트를 조회했습니다."),

    // 201 CREATED
    MEMBER_JOIN(HttpStatus.CREATED, "MEMBER2011", "회원가입이 완료되었습니다."),


    // --- 알림 (NOTIFICATION) 관련 성공 응답 ---

    // 200 OK
    NOTIFICATION_FOUND(HttpStatus.OK, "NOTIF2001", "성공적으로 알림 목록을 조회했습니다."),
    NOTIFICATION_READ(HttpStatus.OK, "NOTIF2002", "알림을 성공적으로 읽음 처리했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
