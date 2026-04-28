package umc10th.assignment.review.exception;

import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
import umc10th.assignment.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
