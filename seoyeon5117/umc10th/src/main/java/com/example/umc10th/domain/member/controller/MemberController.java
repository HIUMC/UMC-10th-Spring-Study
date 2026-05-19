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
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/sign-up")
    public ApiResponse<MemberResDTO.SignUpRes> signUp(@RequestBody MemberReqDTO.SignUp dto) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_CREATED;
        return ApiResponse.onSuccess(code, memberService.signUp(dto));
    }

    // 마이페이지 조회
    @GetMapping("/members/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestParam Long id // 로그인 기능이 없어서 임시로 id로 조회
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET;
        return ApiResponse.onSuccess(code, memberService.getInfo(new MemberReqDTO.GetInfo(id)));
    }

    // 유저 수정
    @PutMapping("/members/me")
    public ApiResponse<MemberResDTO.UpdateInfo> updateInfo(
            @RequestBody MemberReqDTO.UpdateInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_UPDATE;
        return ApiResponse.onSuccess(code, memberService.updateInfo(dto));
    }

    // 내 포인트 조회
    @GetMapping("/members/me/points")
    public ApiResponse<MemberResDTO.GetPoint> getPoint(
            @RequestParam Long id
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_GET_POINT;
        return ApiResponse.onSuccess(code, memberService.getPoint(new MemberReqDTO.GetPoint(id)));
    }

}
