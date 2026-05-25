package com.example.week4.domain.user.controller;

import com.example.week4.domain.user.dto.UserReqDTO;
import com.example.week4.domain.user.dto.UserResDTO;
import com.example.week4.domain.user.exception.code.UserSuccessCode;
import com.example.week4.domain.user.security.AuthUserDetails;
import com.example.week4.domain.user.service.UserService;
import com.example.week4.global.apiPayload.ApiResponse;
import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 마이페이지 조회 - JWT 토큰 기반
    @GetMapping("/v1/users/me")
    public ApiResponse<UserResDTO.MyPageResponse> getMyPage(
            @AuthenticationPrincipal AuthUserDetails authUserDetails
    ) {
        BaseSuccessCode code = UserSuccessCode.GET_MY_PAGE;
        UserResDTO.MyPageResponse response = userService.getMyPage(authUserDetails.getUser());
        return ApiResponse.onSuccess(code, response);
    }

    // 마이페이지 수정
    @PatchMapping("/v1/users/me")
    public ApiResponse<UserResDTO.MyPageResponse> updateMyPage(
            @RequestBody @Valid UserReqDTO.UpdateMyPageRequest dto
    ) {
        BaseSuccessCode code = UserSuccessCode.UPDATE_MY_PAGE;
        UserResDTO.MyPageResponse response = userService.updateMyPage(dto);
        return ApiResponse.onSuccess(code, response);
    }

}
