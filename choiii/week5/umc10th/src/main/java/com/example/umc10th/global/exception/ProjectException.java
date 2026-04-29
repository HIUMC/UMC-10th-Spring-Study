package com.example.umc10th.global.exception;

import com.example.umc10th.global.api.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException{
    private final BaseErrorCode errorCode;
}

