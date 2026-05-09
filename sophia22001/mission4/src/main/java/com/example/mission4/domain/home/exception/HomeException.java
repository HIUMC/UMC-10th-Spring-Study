package com.example.mission4.domain.home.exception;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import com.example.mission4.global.apiPayload.exception.ProjectException;

public class HomeException extends ProjectException {
    public HomeException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
