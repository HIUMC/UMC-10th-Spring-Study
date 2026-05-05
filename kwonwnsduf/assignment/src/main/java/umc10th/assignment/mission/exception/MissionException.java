package umc10th.assignment.mission.exception;

import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
import umc10th.assignment.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
