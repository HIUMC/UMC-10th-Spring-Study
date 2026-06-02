package com.example.demo.global.security.util;

import com.example.demo.global.apiPayload.ApiResponse;
import com.example.demo.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityResponseUtil {

    public static void sendErrorResponse(HttpServletResponse response, BaseErrorCode code) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code.getCode(), code.getMessage(), null);
        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}