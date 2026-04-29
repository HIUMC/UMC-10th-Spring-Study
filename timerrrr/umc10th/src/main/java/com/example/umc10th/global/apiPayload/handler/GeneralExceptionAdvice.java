package com.example.umc10th.global.apiPayload.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 프로젝트에서 발생한 예외 처리
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(ProjectException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    // 그 외 정의되지 않은 모든 예외

    // 잘못된 요청(400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException e) {
        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }

    // 리소스 없음 (404)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNoResourceException(NoResourceFoundException e) {
        BaseErrorCode code = GeneralErrorCode.NOT_FOUND;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }

    // 허용되지 않은 HTTP 메서드 (405)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e) {
        BaseErrorCode code = GeneralErrorCode.METHOD_NOT_ALLOWED;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }

    // 서버 내부 오류 (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }
}
