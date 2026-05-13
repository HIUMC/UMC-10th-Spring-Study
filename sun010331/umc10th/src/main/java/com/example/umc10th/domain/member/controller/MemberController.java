package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping ("api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinDTO> memberSignUp(
            @RequestBody  MemberReqDTO.JoinDTO joinDTO
    ){
        //임시 데이터
        MemberResDTO.JoinDTO result = MemberResDTO.JoinDTO.builder()
                .memberId(1L)
                .createdAt(LocalDateTime.now())
                .build();


        BaseSuccessCode code = MemberSuccessCode.OK;

        return ApiResponse.onSuccess(code,result);
    }


    @GetMapping("me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestParam Long memberId
    ){

        BaseSuccessCode code = MemberSuccessCode.OK;
        return  ApiResponse.onSuccess(code,memberService.getInfo(memberId));
    }


}