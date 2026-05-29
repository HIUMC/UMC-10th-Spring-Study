package com.example.mission4.global.security.exception;

import com.example.mission4.global.apiPayload.code.GeneralErrorCode;
import com.example.mission4.global.security.util.SecurityResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * 로그인은 했지만 권한 없는 사용자 (403 Forbidden)
 */
public class CustomAccessDenied implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        SecurityResponseUtil.writeErrorResponse(response, GeneralErrorCode.FORBIDDEN);
    }
}
