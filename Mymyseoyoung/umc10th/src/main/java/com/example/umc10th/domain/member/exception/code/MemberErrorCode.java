package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

        // 회원 관련 에러 응답
        MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4041", "해당 회원을 찾을 수 없습니다."),
        MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4091", "이미 존재하는 회원입니다."),
        MEMBER_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "아이디 혹은 비밀번호가 틀렸습니다.");

        private final HttpStatus status;
        private final String code;
        private final String message;
}
