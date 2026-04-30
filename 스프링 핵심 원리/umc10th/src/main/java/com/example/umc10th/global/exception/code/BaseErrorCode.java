package com.example.umc10th.global.exception.code;
import org.springframework.http.HttpStatus;
public interface BaseErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
