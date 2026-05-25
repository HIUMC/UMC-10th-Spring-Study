package com.example.week4.global.security.handler;

import com.example.week4.domain.user.converter.UserConverter;
import com.example.week4.domain.user.dto.UserResDTO;
import com.example.week4.domain.user.exception.code.UserSuccessCode;
import com.example.week4.domain.user.security.AuthUserDetails;
import com.example.week4.global.apiPayload.ApiResponse;
import com.example.week4.global.security.entity.OAuthMember;
import com.example.week4.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess (
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        OAuthMember oAuthMember = (OAuthMember) authentication.getPrincipal();

        AuthUserDetails authUserDetails = new AuthUserDetails(oAuthMember.getUser());
        String accessToken = jwtUtil.createAccessToken(authUserDetails);

        UserResDTO.LoginResponse loginResponse = UserConverter.toLoginResponse(accessToken);

        ApiResponse<UserResDTO.LoginResponse> responseBody = ApiResponse.onSuccess(
                UserSuccessCode.LOGIN, loginResponse);

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(UserSuccessCode.LOGIN.getStatus().value());

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
