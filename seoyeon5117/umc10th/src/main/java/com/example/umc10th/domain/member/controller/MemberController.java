package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.enums.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "멤버 관리")
public class MemberController {

    private final MemberService memberService;

    // 마이페이지 조회
    @GetMapping("/v2/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
            ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }

    // 유저 수정
    @PutMapping("/v2/members/me")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_UPDATE;
        return ApiResponse.onSuccess(code, memberService.updateInfo(member));
    }

    // 내 포인트 조회
    @GetMapping("/v2/members/me/points")
    public ApiResponse<MemberResDTO.GetPoint> getPoint(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET_POINT;
        return ApiResponse.onSuccess(code, memberService.getPoint(member));
    }

}
