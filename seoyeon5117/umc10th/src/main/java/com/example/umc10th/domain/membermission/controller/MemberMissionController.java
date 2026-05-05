package com.example.umc10th.domain.membermission.controller;

import com.example.umc10th.domain.membermission.dto.MemberMissionReqDTO;
import com.example.umc10th.domain.membermission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import com.example.umc10th.domain.membermission.enums.MemberMissionSuccessCode;
import com.example.umc10th.domain.membermission.service.MemberMissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    // 멤버 미션 목록 조회 (IN_PROGRESS / COMPLETED)
    @GetMapping
    public ApiResponse<List<MemberMissionResDTO.GetMemberMission>> getMemberMissions(
            @RequestParam Long memberId,
            @RequestParam MemberMissionStatus status
    ) {
        BaseSuccessCode code = MemberMissionSuccessCode.MEMBER_MISSION_GET;
        return ApiResponse.onSuccess(code, memberMissionService.getMemberMissions(memberId, status));
    }

    // 미션 성공 처리
    @PatchMapping("/{missionId}")
    public ApiResponse<MemberMissionResDTO.UpdateMemberMissionStatus> completeMission(
            @PathVariable Long missionId,
            @RequestBody MemberMissionReqDTO.CompleteMission dto
    ) {
        BaseSuccessCode code = MemberMissionSuccessCode.MEMBER_MISSION_COMPLETE;
        return ApiResponse.onSuccess(code, memberMissionService.completeMission(missionId, dto));
    }
}
