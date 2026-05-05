package com.example.umc10th.domain.memberterm.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class MemberTermException extends ProjectException {
    public MemberTermException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
