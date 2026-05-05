package com.example.umc10th.domain.point.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class PointException extends ProjectException {
    public PointException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
