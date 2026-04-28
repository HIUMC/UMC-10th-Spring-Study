package com.example.umc10th.domain.usermission.controller;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.usermission.dto.UserMissionReqDTO;
import com.example.umc10th.domain.usermission.dto.UserMissionResDTO;
import com.example.umc10th.domain.usermission.enums.UserMissionSuccessCode;
import com.example.umc10th.domain.usermission.service.UserMissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class UserMissionController {

    private final UserMissionService userMissionService;

    // 유저 미션 목록 조회 (IN_PROGRESS / COMPLETED)
    @GetMapping
    public ApiResponse<List<UserMissionResDTO.GetUserMission>> getUserMissions(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status
    ) {
        BaseSuccessCode code = UserMissionSuccessCode.USER_MISSION_GET;
        return ApiResponse.onSuccess(code, userMissionService.getUserMissions(memberId, status));
    }

    // 미션 성공 처리
    @PatchMapping("/{missionId}")
    public ApiResponse<UserMissionResDTO.UpdateUserMissionStatus> completeMission(
            @PathVariable Long missionId,
            @RequestBody UserMissionReqDTO.CompleteMission dto
    ) {
        BaseSuccessCode code = UserMissionSuccessCode.USER_MISSION_COMPLETE;
        return ApiResponse.onSuccess(code, userMissionService.completeMission(missionId, dto));
    }
}
