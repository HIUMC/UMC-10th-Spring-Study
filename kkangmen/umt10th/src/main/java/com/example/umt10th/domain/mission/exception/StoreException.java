package com.example.umt10th.domain.mission.exception;

import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import com.example.umt10th.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
  public StoreException(BaseErrorCode errorCode) {super(errorCode);}
}
