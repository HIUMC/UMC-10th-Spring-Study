package com.example.demo.domain.member.controller;

import com.example.demo.domain.member.dto.MemberReqDTO;
import com.example.demo.domain.member.dto.MemberResDTO;
import com.example.demo.domain.member.exception.code.MemberSuccessCode;
import com.example.demo.domain.member.service.MemberService;
import com.example.demo.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지
    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ) {
        MemberSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    // 회원 가입
    @PostMapping("/v1/users")
    public ApiResponse<MemberResDTO.GetInfo> signup(
            @RequestBody MemberReqDTO.SignupRequest dto
    ){
        MemberSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.signup(dto));
    }
}
