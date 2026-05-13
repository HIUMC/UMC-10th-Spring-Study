package com.example.mission4.domain.store.exception.code;

import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode{;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
