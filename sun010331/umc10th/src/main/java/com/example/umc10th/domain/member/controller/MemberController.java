package com.example.umc10th.domain.member.controller;


import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping ("api/members")
public class MemberController {

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinDTO> memberSignUp(
            @RequestBody  MemberReqDTO.JoinDTO joinDTO
    ){
        //임시 데이터
        MemberResDTO.JoinDTO result = MemberResDTO.JoinDTO.builder()
                .memberId(1L)
                .createdAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess("회원가입에 성공했습니다",result);
    }


}