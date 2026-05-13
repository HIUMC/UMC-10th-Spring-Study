package com.example.mission4.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();

}
