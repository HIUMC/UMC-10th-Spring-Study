package com.example.umt10th.domain.auth;

import com.example.umt10th.domain.auth.dto.SignupReqDto;
import com.example.umt10th.domain.auth.dto.SignupResDto;
import com.example.umt10th.domain.auth.exception.code.AuthSuccessCode;
import com.example.umt10th.domain.member.service.MemberService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/auth/sign-up")
    public ApiResponse<SignupResDto.SignupComp> signUp(
            @RequestBody @Valid SignupReqDto.Signup dto
    ){
        BaseSuccessCode successCode = AuthSuccessCode.CREATED;
        return ApiResponse.onSuccess(successCode, memberService.saveMember(dto));
    }
}
