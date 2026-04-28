package umc10th.assignment.store.exception;

import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
import umc10th.assignment.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode){
        super(errorCode);
    }
}
