package com.example.umt10th.domain.member.controller;

import com.example.umt10th.domain.member.dto.MemberResDTO;
import com.example.umt10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umt10th.domain.member.service.MemberService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umt10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    /***
     * 마이페이지
     * @return
     * 추후 추가 - @AuthenticationPrincipal로 유저 확인 및 memberService에 전달
     */
    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }
}
