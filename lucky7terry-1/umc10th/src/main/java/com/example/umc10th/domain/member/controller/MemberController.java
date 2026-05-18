package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/v1/users")
    public ApiResponse<MemberResDTO.SignupDTO> createMember(
            @RequestParam @Valid MemberReqDTO.SignupDTO dto) {

        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.saveMember(dto));

    }

    // 마이페이지
    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getInfo( @RequestHeader Long memberId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.myPage(memberId));
    }
}
