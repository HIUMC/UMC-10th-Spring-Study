package com.example.umt10th.global.security.filter;

import com.example.umt10th.domain.member.enums.SocialType;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import com.example.umt10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umt10th.global.security.service.CustomOAuthService;
import com.example.umt10th.global.security.service.CustomUserDetailsService;
import com.example.umt10th.global.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            // Authorization 헤더에서 토큰 가져오기
            String token = request.getHeader("Authorization");

            // 토큰이 없거나 Bearer가 아니면 넘기기
            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response); // 다음 필터로 넘어가기
                return;
            }

            // Bearer 이면 추출
            token = token.replace("Bearer ", "");

            // jwtUtil로 토큰 검증하기
            if (jwtUtil.isValid(token)){
                // String email = jwtUtil.getEmail(token); // 토큰에서 이메일 추출

                // 인증 객체 생성: 이메일로 찾아온 뒤, 인증 객체(Authentication) 생성
                // 폼 로그인을 하지 않으니까 주석 처리
                // UserDetails user = customUserDetailsService.loadUserByUsername(email);
                // Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                // JWT 토큰에서 유저 정보 조회: UID와 socialType 가져오기
                SocialType socialType = jwtUtil.getSocialType(token);
                String uid = jwtUtil.getUid(token);

                // 인증 객체 생성
                UserDetails member = customUserDetailsService.loadUserByUidAndSocialType(socialType, uid);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        member,
                        null,
                        member.getAuthorities()
                );

                // 인증 완료 후 SecurityContextHolder에 넣기
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            BaseErrorCode errorCode = GeneralErrorCode.UNAUTHORIZED;

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(errorCode.getStatus().value());

            ApiResponse<Void> errorResponse = ApiResponse.onFailure(errorCode, null);

            mapper.writeValue(response.getOutputStream(), errorResponse);
            return;
        }
        filterChain.doFilter(request, response); // 다음 필터로 넘어가기

    }
}
