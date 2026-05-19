package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberRequestDTO;
import com.example.umc10th.domain.member.dto.MemberResponseDTO;
import com.example.umc10th.domain.member.enums.MemberSuccessCode;
import com.example.umc10th.domain.member.service.AuthService;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
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
    @PostMapping("/sign-up")
    public ApiResponse<MemberResponseDTO.JoinResult> signup(@RequestBody @Valid MemberRequestDTO.Join request) {
        // 회원가입 로직 호출
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_JOINED, authService.join(request));
    }
}
