package com.example.umc10th.domain.notification.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class NotificationException extends ProjectException {
    public NotificationException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
