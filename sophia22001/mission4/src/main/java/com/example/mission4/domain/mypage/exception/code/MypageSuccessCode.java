package com.example.mission4.domain.mypage.exception.code;

import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MypageSuccessCode implements BaseSuccessCode {

    HOME_FOUND(HttpStatus.OK, "MYPAGE200_1", "성공적으로 마이페이지 정보를 조회했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
