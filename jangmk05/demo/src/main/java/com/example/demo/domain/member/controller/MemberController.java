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

    @GetMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestParam Long id
    ) {
        MemberSuccessCode code = MemberSuccessCode.MEMBER_FOUND;
        MemberReqDTO.GetInfo dto = new MemberReqDTO.GetInfo(id);
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

}
