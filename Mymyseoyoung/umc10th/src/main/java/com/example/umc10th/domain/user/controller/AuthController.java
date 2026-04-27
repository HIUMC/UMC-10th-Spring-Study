package com.example.umc10th.domain.user.controller;


import com.example.umc10th.domain.user.dto.MemberRequestDTO;
import com.example.umc10th.domain.user.dto.MemberResponseDTO;
import com.example.umc10th.domain.user.enums.MemberSuccessCode;
import com.example.umc10th.domain.user.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberResponseDTO.JoinResult> join(@RequestBody MemberRequestDTO.Join request) {
        // 회원가입 로직 호출
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_JOINED, memberService.join(request));
    }
}
