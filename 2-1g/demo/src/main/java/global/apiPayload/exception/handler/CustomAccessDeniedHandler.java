package global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.apiPayload.ApiResponse;
import global.code.GeneralErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException
    {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(403);

        // FORBIDDEN 규격으로 에러 메시지 생성
        ApiResponse<Object> errorResponse = ApiResponse.onFailure(GeneralErrorCode.FORBIDDEN, null);

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}