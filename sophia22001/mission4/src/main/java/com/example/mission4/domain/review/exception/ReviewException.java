package com.example.mission4.domain.review.exception;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import com.example.mission4.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
