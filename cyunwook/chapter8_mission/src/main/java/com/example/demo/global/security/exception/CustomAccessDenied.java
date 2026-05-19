package com.example.demo.global.security.exception;

import com.example.demo.global.apiPayload.code.GeneralErrorCode;
import com.example.demo.global.security.util.SecurityResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;

public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        SecurityResponseUtil.sendErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}