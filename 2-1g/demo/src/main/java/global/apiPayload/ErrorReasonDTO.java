package global.apiPayload;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {

    private final HttpStatus status;
    private final String code;
    private final String message;
}
