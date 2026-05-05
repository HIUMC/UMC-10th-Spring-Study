package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    // 회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 지역 변경
    @PatchMapping("/members/me/region")
    public ApiResponse<MemberResDTO.UpdateRegionResultDTO> updateRegion(
            @RequestBody MemberReqDTO.UpdateRegionDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

}
