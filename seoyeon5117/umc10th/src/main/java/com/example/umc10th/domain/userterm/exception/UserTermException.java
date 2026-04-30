package com.example.umc10th.domain.userterm.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class UserTermException extends ProjectException {
    public UserTermException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
