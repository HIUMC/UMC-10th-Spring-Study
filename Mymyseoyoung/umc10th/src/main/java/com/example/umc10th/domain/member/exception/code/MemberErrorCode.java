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

        // 400 BAD_REQUEST
        MEMBER_ID_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "아이디 혹은 비밀번호가 틀렸습니다."),

        // 401 UNAUTHORIZED
        INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER4011", "비밀번호가 일치하지 않습니다."),

        // 404 NOT_FOUND
        MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4041", "해당 회원을 찾을 수 없습니다."),

        // 409 CONFLICT
        MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4091", "이미 존재하는 회원입니다."),
        EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER4092", "이미 존재하는 이메일입니다.");
        private final HttpStatus status;
        private final String code;
        private final String message;
}
