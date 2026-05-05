package umc10th.assignment.global.apiPayload.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc10th.assignment.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {

    private final BaseErrorCode errorCode;}


