package com.example.mission4.global.apiPayload;

import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 만든다.
@JsonPropertyOrder({"isSuccess", "code", "message", "result"}) // JSON 내 순서 지정
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 (result 포함)
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result );
    }

    // 실패한 경우 (result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result );
    }
}
