package com.example.demo.global.security.exception;

import com.example.demo.global.apiPayload.code.GeneralErrorCode;
import com.example.demo.global.security.util.SecurityResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        SecurityResponseUtil.sendErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}