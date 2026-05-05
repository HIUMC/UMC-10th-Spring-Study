package global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import global.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {
    // 5주차 미션 범위에서는 모든 API가 같은 형태의 JSON을 반환하도록 공통 응답 객체로 통일했다.

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, "COMMON200", "성공입니다.", result);
    }

    public static <T> ApiResponse<T> of(Boolean isSuccess, String code, String message, T result) {
        return new ApiResponse<>(isSuccess, code, message, result);
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode e, T result) {
        return new ApiResponse<>(false, e.getCode(), e.getMessage(), result);
    }
}
