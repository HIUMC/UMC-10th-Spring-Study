package com.example.umc10th.global.apiPayload.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    // @Valid 검증 실패 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        // 검증 실패한 변수명(Field)과 실패 이유(Message)를 담을 Map
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        BaseErrorCode code = GeneralErrorCode.BAD_REQUEST;
        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errors));

    }

    // 비즈니스 로직(Service) 커스텀 예외 처리
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Object>> handleProjectException(ProjectException e) {
        // 커스텀 예외가 품고 있는 BaseErrorCode를 그대로 꺼내서 ApiResponse에 넘깁니다.
        BaseErrorCode code = e.getErrorCode();

        return ResponseEntity.status(code.getStatus().value())
                .body(ApiResponse.onFailure(code, null));
    }

    // 그 외 알 수 없는 서버 에러 (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleAllException(Exception e) {
        e.printStackTrace();

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR; // 500 에러용 Enum

        return ResponseEntity.status(code.getStatus().value())
                .body(ApiResponse.onFailure(code, e.getMessage()));
    }
}