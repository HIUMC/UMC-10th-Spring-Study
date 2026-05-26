package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.dto.TokenResponse;
import com.example.umc10th.domain.member.enums.MemberSuccessCode;
import com.example.umc10th.domain.member.service.AuthService;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.util.HeaderUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
private final AuthService authService;
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.JoinResult> signup(@RequestBody @Valid MemberRequestDTO.Join request, HttpServletResponse response) {
        MemberResponseDTO.JoinResult result = authService.join(request);
        HeaderUtil.setAuthorizationHeader(response, result.accessToken());
        // 회원가입 로직 호출
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_JOINED, result);
    }


}
