package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.global.exception.code.BaseErrorCode;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
