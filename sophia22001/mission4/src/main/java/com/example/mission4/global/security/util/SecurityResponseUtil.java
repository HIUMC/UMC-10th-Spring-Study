package com.example.mission4.global.security.util;

import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;


public class SecurityResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper(); // static: 클래스 로딩 시 딱 한 번만 생성

    /**
     * SecurityResponseUtil은 인스턴스를 만들 필요가 없는 유틸 클래스
     * -> static으로 선언한다.
     */
    public static void writeErrorResponse(HttpServletResponse response, BaseErrorCode code) throws IOException {

        // 응답 Content-Type, HTTP 상태코드 정의
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // Response Body에 응답 통일한 객체를 넣기
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        // 실제 Response로 덮어쓰기
        objectMapper.writeValue(response.getOutputStream(), errorResponse);

    }



}
