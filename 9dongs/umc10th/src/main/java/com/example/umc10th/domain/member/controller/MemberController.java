package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request
    ) {
        Member member = memberService.signUp(request);
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, MemberConverter.toSignUpResultDTO(member));
    }

    // 로그인
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody MemberReqDTO.LoginDTO request
    ) {
        String accessToken = memberService.login(request);
        return ApiResponse.onSuccess(
                MemberSuccessCode.OK,
                MemberConverter.toLoginResultDTO(accessToken)
        );
    }

    // 마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(@AuthenticationPrincipal AuthMember member) {
        MemberResDTO.MyPageDTO result = memberService.getMyPage(member);
        return ApiResponse.onSuccess(MemberSuccessCode.OK, result);
    }

    // 지역 변경
    @PatchMapping("/members/me/region")
    public ApiResponse<MemberResDTO.UpdateRegionResultDTO> updateRegion(
            @RequestBody MemberReqDTO.UpdateRegionDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

}
