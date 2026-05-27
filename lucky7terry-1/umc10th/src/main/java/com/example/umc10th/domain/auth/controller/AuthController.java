package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.auth.service.AuthService;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final MemberService memberService;

    /**
     * 회원가입
     */
    @PostMapping("/sign-up")
    public ApiResponse<AuthResDTO.SignupDTO> signUp(
            @RequestBody @Valid AuthReqDTO.SignupDTO dto) {

        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, authService.saveMember(dto));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ApiResponse<String> login(
            @RequestBody AuthReqDTO.LoginDTO dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, authService.login(dto));
    }
}
