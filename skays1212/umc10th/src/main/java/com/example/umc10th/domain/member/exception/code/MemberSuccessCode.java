package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER201_1", "멤버가 성공적으로 생성되었습니다."),
    MEMBER_FOUND(HttpStatus.OK, "MEMBER200_1", "멤버 조회에 성공했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
