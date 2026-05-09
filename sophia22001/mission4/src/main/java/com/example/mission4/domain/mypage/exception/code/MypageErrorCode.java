package com.example.mission4.domain.mypage.exception.code;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MypageErrorCode implements BaseErrorCode {;



    private final HttpStatus status;
    private final String code;
    private final String message;

}
