package com.example.umc10th.domain.inquiry.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class InquiryException extends ProjectException {
    public InquiryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
