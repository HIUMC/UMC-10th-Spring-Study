package com.example.mission4.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
