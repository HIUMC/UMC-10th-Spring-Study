package com.example.umc10th.global.security.filter;

import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.security.service.CustomUserDetailsService;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            // 토큰 가져오기
            String token = request.getHeader("Authorization");
            // token이 없거나 Bearer가 아니면 넘기기
            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // Bearer이면 추출
            token = token.replace("Bearer ", "");
            // AccessToken 검증하기: 올바른 토큰이면
            if (jwtUtil.isValid(token)) {

                UserDetails userDetails;

                if (jwtUtil.isSocialUidNull(token)) {
                    // 토큰에서 이메일 추출
                    String email = jwtUtil.getEmail(token);
                    userDetails = customUserDetailsService.loadUserByUsername(email);
                } else {
                    String socialUid = jwtUtil.getSocialUid(token);
                    SocialType socialType = jwtUtil.getSocialType(token);
                    userDetails = customUserDetailsService.loadUserByUidAndSocialType(socialType,
                            socialUid);
                }

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // 인증 완료 후 SecurityContextHolder에 넣기
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(code.getStatus().value());

            ApiResponse<Void> errorResponse = ApiResponse.onFailure(code,null);

            mapper.writeValue(response.getOutputStream(), errorResponse);
        }
    }
}