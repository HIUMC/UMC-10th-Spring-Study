package com.example.umc10th.domain.auth.controller;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.enums.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "회원가입 및 로그인")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/v1/auth/sign-up")
    public ApiResponse<AuthResDTO.SignUp> signUp(@RequestBody AuthReqDTO.SignUp dto) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_CREATED;
        return ApiResponse.onSuccess(code, memberService.signUp(dto));
    }

    @PostMapping("/v1/auth/login")
    public ApiResponse<AuthResDTO.Login> login(@RequestBody AuthReqDTO.Login dto) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET;
        return ApiResponse.onSuccess(code, memberService.login(dto));
    }

}
