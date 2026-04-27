package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* ───────────── 회원가입 POST /auth/signup ───────────── */
    @PostMapping("/auth/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResDTO.SignupResDTO> signup(
            @Valid @RequestBody MemberReqDTO.SignupReqDTO request) {

        MemberResDTO.SignupResDTO response = memberService.signup(request);
        return ApiResponse.onSuccess(response);
    }

    /* ───────────── 로그인 POST /auth/login ───────────── */
    @PostMapping("/auth/login")
    public ApiResponse<MemberResDTO.LoginResDTO> login(
            @Valid @RequestBody MemberReqDTO.LoginReqDTO request) {

        MemberResDTO.LoginResDTO response = memberService.login(request);
        return ApiResponse.onSuccess(response);
    }

    /* ───────────── 회원탈퇴 DELETE /users/me ───────────── */
    // Authorization: Bearer <token> → Security 필터에서 인증 처리
    @DeleteMapping("/users/me")
    public ApiResponse<Void> withdraw() {
        memberService.withdraw();
        return ApiResponse.onSuccess(null);
    }

    /* ───────────── 내 정보 조회 GET /api/users/me ───────────── */
    @GetMapping("/api/users/me")
    public ApiResponse<MemberResDTO.MyInfoResDTO> getMyInfo() {
        MemberResDTO.MyInfoResDTO response = memberService.getMyInfo();
        return ApiResponse.onSuccess(response);
    }

    /* ───────────── 1:1 문의 POST /api/users/qna ───────────── */
    @PostMapping("/api/users/qna")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> createQna(
            @Valid @RequestBody MemberReqDTO.QnaReqDTO request) {

        memberService.createQna(request);
        return ApiResponse.onSuccess(null);
    }

    /* ───────────── 닉네임 수정 PATCH /api/users/me/nickname ───────────── */
    @PatchMapping("/api/users/me/nickname")
    public ApiResponse<MemberResDTO.NicknameUpdateResDTO> updateNickname(
            @Valid @RequestBody MemberReqDTO.NicknameUpdateReqDTO request) {

        MemberResDTO.NicknameUpdateResDTO response = memberService.updateNickname(request);
        return ApiResponse.onSuccess(response);
    }

    /* ───────────── 알림 설정 PATCH /api/users/me/notice ───────────── */
    @PatchMapping("/api/users/me/notice")
    public ApiResponse<MemberResDTO.NoticeSettingResDTO> updateNoticeSetting(
            @Valid @RequestBody MemberReqDTO.NoticeSettingReqDTO request) {

        MemberResDTO.NoticeSettingResDTO response = memberService.updateNoticeSetting(request);
        return ApiResponse.onSuccess(response);
    }
}
