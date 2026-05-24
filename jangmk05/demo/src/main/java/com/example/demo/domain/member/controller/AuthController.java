package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.exception.code.MemberSuccessCode;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.SignupResponse> signup(
            @RequestBody MemberReqDTO.SignupRequest request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.MEMBER_CREATED,
                memberService.signup(request)
        );
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResponse> login(
            @RequestBody MemberReqDTO.LoginRequest request
    ) {
        return ApiResponse.onSuccess(
                // 성공 코드 변경 (필요시)
                MemberSuccessCode.MEMBER_LOGIN_SUCCESS,
                memberService.login(request)
        );
    }
}