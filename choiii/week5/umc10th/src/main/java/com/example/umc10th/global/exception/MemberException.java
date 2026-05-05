package com.example.umc10th.global.exception;

import com.example.umc10th.global.api.code.BaseErrorCode;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) { super(errorCode);}
}