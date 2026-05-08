package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/auth/users")
    public ApiResponse<String> join(@RequestBody MemberReqDTO.JoinDTO request) {
        return ApiResponse.onSuccess("회원가입 성공");
    }

    @GetMapping("/api/users/{memberId}")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    @GetMapping("/api/users/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam String status,
            @RequestParam(defaultValue = "1") int page) {
        return ApiResponse.onSuccess(memberService.getMyMissions(memberId, status, page));
    }
}