package com.example.umc10th.global.security.controller;

import com.example.umc10th.domain.user.dto.request.UserCreateRequest;
import com.example.umc10th.domain.user.dto.response.UserResponse;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.api.ApiResponse;
import com.example.umc10th.global.api.code.MemberSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<UserResponse> signUp(
            @RequestBody @Valid UserCreateRequest request
    ) {
        return ApiResponse.onSuccess(
                MemberSuccessCode.CREATED,
                userService.createUser(request)
        );
    }
}