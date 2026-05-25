package com.example.week4.domain.user.controller;

import com.example.week4.domain.user.dto.UserReqDTO;
import com.example.week4.domain.user.dto.UserResDTO;
import com.example.week4.domain.user.exception.code.UserSuccessCode;
import com.example.week4.domain.user.service.UserService;
import com.example.week4.global.apiPayload.ApiResponse;
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

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResDTO.SignUpResponse> signUp(
            @RequestBody @Valid UserReqDTO.SignUpRequest dto
    ) {
        UserResDTO.SignUpResponse response = userService.signUp(dto);
        return ApiResponse.onSuccess(UserSuccessCode.SIGN_UP, response);
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginResponse> login(
            @RequestBody @Valid UserReqDTO.LoginRequest dto
    ) {
        UserResDTO.LoginResponse response = userService.login(dto);
        return ApiResponse.onSuccess(UserSuccessCode.LOGIN, response);
    }

}
