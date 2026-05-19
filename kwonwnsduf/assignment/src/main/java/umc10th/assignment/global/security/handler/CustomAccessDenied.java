package umc10th.assignment.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import umc10th.assignment.global.apiPayload.ApiResponse;
import umc10th.assignment.global.apiPayload.code.BaseErrorCode;
import umc10th.assignment.global.apiPayload.code.status.GeneralErrorCode;

import java.io.IOException;

public class CustomAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.FORBIDDEN;

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
