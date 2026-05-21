package com.example.mission4.domain.member.controller;

import com.example.mission4.domain.member.dto.MemberReqDTO;
import com.example.mission4.domain.member.dto.MemberResDTO;
import com.example.mission4.domain.member.exception.MemberException;
import com.example.mission4.domain.member.exception.code.MemberErrorCode;
import com.example.mission4.domain.member.exception.code.MemberSuccessCode;
import com.example.mission4.domain.member.service.MemberService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
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

    // 유저 조회 - 유저 정보 반환
    @PostMapping("/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestParam Long memberId
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_FOUND;
        return ApiResponse.onSuccess(code, memberService.getInfo(memberId)); // code, result
    }

    // 유저 회원가입 - 유저 id 반환
    @PostMapping("/users/signup")
    public ApiResponse<MemberResDTO.SignUp> signUp(
            @RequestBody @Valid MemberReqDTO.SignUp dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_SIGNUP;
        return ApiResponse.onSuccess(code, memberService.signup(dto));
    }



}
