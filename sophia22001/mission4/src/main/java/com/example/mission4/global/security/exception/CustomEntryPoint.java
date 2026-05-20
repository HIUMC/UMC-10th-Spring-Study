package com.example.mission4.global.security.exception;

import com.example.mission4.global.apiPayload.code.GeneralErrorCode;
import com.example.mission4.global.security.util.SecurityResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 로그인 안 한 사용자 (401 Unauthorized)
 */
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        SecurityResponseUtil.writeErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}
