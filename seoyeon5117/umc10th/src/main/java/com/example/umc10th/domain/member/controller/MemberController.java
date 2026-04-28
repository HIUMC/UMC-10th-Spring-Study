package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.enums.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    // 유저 조회
    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestParam Long id
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET;
        return ApiResponse.onSuccess(code, memberService.getInfo(new MemberReqDTO.GetInfo(id)));
    }

    // 유저 수정
    @PutMapping("/me")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @RequestBody MemberReqDTO.UpdateInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_UPDATE;
        return ApiResponse.onSuccess(code, memberService.updateInfo(dto));
    }

    // 내 포인트 조회
    @GetMapping("/points")
    public ApiResponse<MemberResDTO.GetPoint> getPoint(
            @RequestParam Long id
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET_POINT;
        return ApiResponse.onSuccess(code, memberService.getPoint(new MemberReqDTO.GetPoint(id)));
    }

}
