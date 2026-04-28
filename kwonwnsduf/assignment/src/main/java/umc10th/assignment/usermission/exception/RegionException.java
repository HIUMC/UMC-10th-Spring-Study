package umc10th.assignment.usermission.exception;

import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
import umc10th.assignment.global.apiPayload.exception.ProjectException;

public class RegionException extends ProjectException {
    public RegionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
