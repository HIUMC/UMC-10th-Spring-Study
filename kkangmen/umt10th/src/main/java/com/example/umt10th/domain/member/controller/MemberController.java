package com.example.umt10th.domain.member.controller;

import com.example.umt10th.domain.member.dto.MemberReqDTO;
import com.example.umt10th.domain.member.dto.MemberResDTO;
import com.example.umt10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umt10th.domain.member.service.MemberService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/v1/users")
    public ApiResponse<MemberResDTO.saveSuccessMember> createMember(
            @RequestBody @Valid MemberReqDTO.saveMember dto){

        BaseSuccessCode code = MemberSuccessCode.OK_2;
        return ApiResponse.onSuccess(code, memberService.saveMember(dto));
    }


    /***
     * 마이페이지
     * @return
     * 추후 추가 - @AuthenticationPrincipal로 유저 확인 및 memberService에 전달
     */
    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo());
    }
}
