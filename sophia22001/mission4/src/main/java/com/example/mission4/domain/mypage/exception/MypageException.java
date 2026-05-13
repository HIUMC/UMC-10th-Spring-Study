package com.example.mission4.domain.mypage.exception;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import com.example.mission4.global.apiPayload.exception.ProjectException;

public class MypageException extends ProjectException {
    public MypageException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
