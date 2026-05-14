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

    // 오프셋 기반 페이지네이션으로 진행중인 미션 조회
    @GetMapping("/api/users/{memberId}/missions")
    public ApiResponse<MissionResDTO.MissionPageDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "false") Boolean complete,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(memberService.getMyMissions(memberId, complete, page, size));
    }
}