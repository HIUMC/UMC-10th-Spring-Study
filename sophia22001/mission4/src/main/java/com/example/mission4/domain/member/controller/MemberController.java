package com.example.mission4.domain.member.controller;

import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.exception.code.MemberSuccessCode;
import com.example.mission4.domain.member.service.MemberService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;



    // 예시 요청 (멤버를 찾을 수 없음)
    @GetMapping("/test")
    public ApiResponse<String> test() {
        // throw new Exception("test");
        throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
    }

    // 유저 조회
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto)); // code, result


    }


}
