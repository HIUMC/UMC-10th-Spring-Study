package com.example.umc10th.global.security.exception;

import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.security.util.SecurityHandlerUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        System.out.println(authException.getMessage());
        authException.printStackTrace();
        SecurityHandlerUtil.sendErrorResponse(response, GeneralErrorCode.UNAUTHORIZED);
    }
}
