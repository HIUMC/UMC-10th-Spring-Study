package com.example.demo.domain.auth.controller;

import com.example.demo.domain.auth.dto.AuthReqDTO;
import com.example.demo.domain.auth.service.AuthService;
import com.example.demo.domain.member.exception.code.MemberSuccessCode;
import com.example.demo.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @Operation(summary = "회원가입")
    public ApiResponse<Long> signUp(@RequestBody @Valid AuthReqDTO.SignUp request) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, authService.signUp(request));
    }
}