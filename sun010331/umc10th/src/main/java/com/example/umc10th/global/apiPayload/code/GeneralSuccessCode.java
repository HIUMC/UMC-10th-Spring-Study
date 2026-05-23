package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "COMMON2000", "성공적으로 처리했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
