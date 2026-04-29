package com.example.umc10th.global.api.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 유저를 조회했습니다."),

    CREATED(
            HttpStatus.CREATED,
            "MEMBER201_1",
                    "성공적으로 회원이 생성되었습니다."
    ),

    TERMS_AGREED(
            HttpStatus.OK,
            "MEMBER200_2",
                    "약관 동의가 완료되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
