package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class MemberMissionException extends ProjectException {
  public MemberMissionException(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
