package global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.apiPayload.ApiResponse;
import global.code.GeneralErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON으로 바꿔주는 기계

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 응답 형식을 JSON으로 설정
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(401);

        // 우리가 만든 ApiResponse 규격으로 에러 메시지 생성
        ApiResponse<Object> errorResponse = ApiResponse.onFailure(GeneralErrorCode.UNAUTHORIZED, null);

        // JSON 문자열로 변환해서 클라이언트에게 전송
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}