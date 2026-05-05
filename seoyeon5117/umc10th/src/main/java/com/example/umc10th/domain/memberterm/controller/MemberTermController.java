package com.example.umc10th.domain.memberterm.controller;

import com.example.umc10th.domain.memberterm.dto.MemberTermReqDTO;
import com.example.umc10th.domain.memberterm.dto.MemberTermResDTO;
import com.example.umc10th.domain.memberterm.enums.MemberTermSuccessCode;
import com.example.umc10th.domain.memberterm.service.MemberTermService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member-terms")
public class MemberTermController {

    private final MemberTermService memberTermService;

    // 첫 약관 동의
    @PostMapping
    public ApiResponse<MemberTermResDTO.AgreeTerm> agreeTerm(
            @RequestBody MemberTermReqDTO.AgreeTerm dto
    ) {
        BaseSuccessCode code = MemberTermSuccessCode.MEMBER_TERM_AGREE;
        return ApiResponse.onSuccess(code, memberTermService.agreeTerm());
    }

    // 멤버 약관 목록 조회
    @GetMapping
    public ApiResponse<List<MemberTermResDTO.GetMemberTerm>> getMemberTerms(
            @RequestParam Long memberId
    ) {
        BaseSuccessCode code = MemberTermSuccessCode.MEMBER_TERM_GET;
        return ApiResponse.onSuccess(code, memberTermService.getMemberTerms());
    }
}
