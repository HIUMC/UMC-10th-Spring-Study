package com.example.week4.domain.user.exception;

import com.example.week4.global.apiPayload.code.BaseErrorCode;
import com.example.week4.global.exception.ProjectException;

public class UserException extends ProjectException {

    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
