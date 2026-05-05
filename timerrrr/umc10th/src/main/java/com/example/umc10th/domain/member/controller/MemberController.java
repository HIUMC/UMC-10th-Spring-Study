package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 홈 화면 - 내 포인트, 미션 진행률 조회
    @GetMapping("/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @PathVariable Long memberId
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(new MemberReqDTO.GetInfo(memberId)));
    }

    // 회원가입
    @PostMapping("/members")
    public ApiResponse<Void> signUp(
            @RequestBody MemberReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        memberService.signUp(dto);
        return ApiResponse.onSuccess(code, null);
    }
}
