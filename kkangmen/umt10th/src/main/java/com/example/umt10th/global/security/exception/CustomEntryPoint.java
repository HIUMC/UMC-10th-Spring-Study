package com.example.umt10th.global.security.exception;

import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import com.example.umt10th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        log.error("소셜 로그인 실패 원인: {}", authException.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

        // 응답 Content-Type, HTTP 상태코드 정의
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // response body에 응답 통일한 객체를 넣기
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        // 실제 response로 덮어쓰기
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
