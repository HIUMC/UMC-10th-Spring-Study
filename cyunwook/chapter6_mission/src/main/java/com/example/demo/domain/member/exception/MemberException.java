package com.example.demo.domain.member.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.ProjectException;
import com.example.demo.domain.member.exception.code.MemberErrorCode;

public class MemberException extends ProjectException { // ProjectException 상속!

    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}