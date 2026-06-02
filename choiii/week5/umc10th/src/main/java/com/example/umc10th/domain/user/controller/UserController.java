package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.request.UserCreateRequest;
import com.example.umc10th.domain.user.dto.request.UserGetRequest;
import com.example.umc10th.domain.user.dto.request.UserTermsRequest;
import com.example.umc10th.domain.user.dto.response.UserResponse;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.api.ApiResponse;
import com.example.umc10th.global.api.code.BaseSuccessCode;
import com.example.umc10th.global.api.code.MemberSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/me")
    public ApiResponse<UserResponse> getUser(
//            @RequestBody UserGetRequest request
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
//        return ApiResponse.onSuccess(code, userService.getUser(request.userId())
        return ApiResponse.onSuccess(code, userService.getUser(member)
        );
    }

    @PostMapping("/{userId}/terms")
    public ApiResponse<Void> agreeTerms(@PathVariable Long userId, @RequestBody @Valid UserTermsRequest request) {
        userService.agreeTerms(userId, request);
        return ApiResponse.onSuccess(  MemberSuccessCode.TERMS_AGREED,
                null);
    }
}
