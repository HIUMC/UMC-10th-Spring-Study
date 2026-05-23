package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

}
