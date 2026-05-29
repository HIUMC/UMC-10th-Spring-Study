package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // Public API - 회원가입
    @PostMapping("/auth/sign-up")
    public ApiResponse<String> join(@RequestBody @Valid MemberReqDTO.JoinDTO request) {
        memberService.join(request);
        return ApiResponse.onSuccess("회원가입 성공");
    }

    // Public API - 로그인
    @PostMapping("/auth/sign-in")
    public ApiResponse<MemberResDTO.Login> login(@RequestBody @Valid MemberReqDTO.LoginDTO request) {
        return ApiResponse.onSuccess(memberService.login(request));
    }

    // Private API - 마이페이지 (JWT 토큰에서 인증 객체 추출)
    @GetMapping("/api/users/me")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(@AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.onSuccess(memberService.getMyPage(authMember));
    }

    // Private API - 내 미션 목록 조회
    @GetMapping("/api/users/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionPageDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "false") Boolean complete,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(memberService.getMyMissions(memberId, complete, page, size));
    }
}