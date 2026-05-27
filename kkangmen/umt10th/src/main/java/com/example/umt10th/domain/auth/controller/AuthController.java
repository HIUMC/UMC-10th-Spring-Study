package com.example.umt10th.domain.auth.controller;

import com.example.umt10th.domain.auth.dto.req.LoginReqDto;
import com.example.umt10th.domain.auth.dto.req.SignupReqDto;
import com.example.umt10th.domain.auth.dto.res.LoginResDto;
import com.example.umt10th.domain.auth.dto.res.SignupResDto;
import com.example.umt10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umt10th.domain.auth.service.AuthService;
import com.example.umt10th.domain.member.service.MemberService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
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

    /***
     * 회원가입
     * @param dto
     * @return
     */
    @PostMapping("/sign-up")
    public ApiResponse<SignupResDto.SignupComp> signUp(
            @RequestBody @Valid SignupReqDto.Signup dto
    ){
        BaseSuccessCode successCode = AuthSuccessCode.CREATED;
        return ApiResponse.onSuccess(successCode, authService.saveMember(dto));
    }

    /***
     * 로그인 요청
     */
    @PostMapping("/login")
    public ApiResponse<LoginResDto.LoginRes> login(
            @RequestBody @Valid LoginReqDto.LoginReq dto
            ){
        BaseSuccessCode successCode = AuthSuccessCode.OK;
        return ApiResponse.onSuccess(successCode,authService.login(dto));
    }
}
