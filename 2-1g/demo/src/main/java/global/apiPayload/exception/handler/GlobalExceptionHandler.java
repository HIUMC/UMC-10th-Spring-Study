package global.apiPayload.exception.handler;

import global.apiPayload.ApiResponse;
import global.apiPayload.exception.GeneralException;
import global.code.GeneralErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 워크북에서 설명한 전역 예외 처리 흐름을 따르기 위해 컨트롤러 밖에서 공통 에러 응답을 내려준다.

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException exception) {
        return ResponseEntity
                .status(exception.getErrorCode().getStatus())
                .body(ApiResponse.onFailure(exception.getErrorCode(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception exception) {
        return ResponseEntity
                .status(GeneralErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.INTERNAL_SERVER_ERROR, null));
    }
}
