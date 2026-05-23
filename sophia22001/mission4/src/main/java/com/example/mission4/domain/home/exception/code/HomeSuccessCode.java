package com.example.mission4.domain.home.exception.code;

import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HomeSuccessCode implements BaseSuccessCode {

    HOME_FOUND(HttpStatus.OK, "HOME200_1", "성공적으로 홈 정보를 조회했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
