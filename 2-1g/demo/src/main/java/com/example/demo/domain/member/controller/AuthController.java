package com.example.demo.domain.member.controller;


import com.example.demo.domain.member.dto.MemberRequestDTO;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.service.MemberService;
import global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MemberService memberService;

    // 회원가입 API (POST /auth/signup)
    @PostMapping("/signup")
    public ApiResponse<String> join(
            @RequestBody @Valid MemberRequestDTO.SignUpRequest request
    ) {
        Member member = memberService.joinMember(request);

        return ApiResponse.onSuccess("회원가입이 완료되었습니다. 회원 ID: " + member.getId());
    }
}
