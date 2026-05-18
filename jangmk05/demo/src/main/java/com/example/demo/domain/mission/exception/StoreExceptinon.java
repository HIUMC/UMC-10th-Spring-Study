package com.example.demo.domain.mission.exception;

import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.example.demo.global.apiPayload.exception.ProjectException;

public class StoreExceptinon extends ProjectException {
    public StoreExceptinon(BaseErrorCode code) {
        super(code);
    }
}
