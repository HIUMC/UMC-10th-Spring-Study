package com.example.mission4.domain.auth.controller;

import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.exception.code.MemberSuccessCode;
import com.example.mission4.domain.member.service.MemberService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
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

    private final MemberService memberService;

    // 유저 회원가입 - 유저 id 반환
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @RequestBody @Valid MemberReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_SIGNUP;
        return ApiResponse.onSuccess(code, memberService.signup(dto));
    }

    // 유저 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> login(
            @RequestBody @Valid MemberReqDTO.Login dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_LOGIN;
        return ApiResponse.onSuccess(code, memberService.login(dto));
    }

}
