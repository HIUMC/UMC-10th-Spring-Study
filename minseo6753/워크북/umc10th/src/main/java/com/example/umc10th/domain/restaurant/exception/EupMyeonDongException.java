package com.example.umc10th.domain.restaurant.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class EupMyeonDongException extends ProjectException {
    public EupMyeonDongException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
