package com.example.umc10th.domain.member.enums;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    // 1. 조회 성공 (기존 OK를 구체화)
    MEMBER_FOUND(HttpStatus.OK, "MEMBER200", "성공적으로 유저를 조회했습니다."),

    // 2. 회원가입 성공 (201 Created 사용)
    MEMBER_JOINED(HttpStatus.CREATED, "MEMBER201", "회원가입이 완료되었습니다."),

    // 3. 정보 수정 성공
    MEMBER_UPDATED(HttpStatus.OK, "MEMBER200_1", "회원 정보가 수정되었습니다."),

    // 4. 포인트 조회 성공
    MEMBER_POINT_FOUND(HttpStatus.OK, "MEMBER200_2", "성공적으로 포인트를 조회했습니다."),

    // 1. 알림 목록 조회 성공
    NOTIFICATION_FOUND(HttpStatus.OK, "NOTIF200_1", "성공적으로 알림 목록을 조회했습니다."),

    // 2. 알림 읽음 처리 성공 (상태 변경이므로 OK 혹은 204 No Content도 사용하지만 200이 무난합니다)
    NOTIFICATION_READ(HttpStatus.OK, "NOTIF200_2", "알림을 성공적으로 읽음 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
