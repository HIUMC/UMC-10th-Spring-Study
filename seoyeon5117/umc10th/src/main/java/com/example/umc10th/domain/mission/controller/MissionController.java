package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionController {

    private final MissionService missionService;

    // 미션 등록
    @PostMapping
    public ApiResponse<MissionResDTO.CreateMission> createMission(
            @RequestBody MissionReqDTO.CreateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_CREATE;
        return ApiResponse.onSuccess(code, missionService.createMission(dto));
    }

    // 미션 조회
    @GetMapping("/{missionId}")
    public ApiResponse<MissionResDTO.GetMission> getMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET;
        return ApiResponse.onSuccess(code, missionService.getMission(missionId));
    }

    // 미션 목록 조회
    @GetMapping
    public ApiResponse<List<MissionResDTO.GetMission>> getMissions(
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSIONS_GET;
        return ApiResponse.onSuccess(code, missionService.getMissions());
    }

    // 미션 수정
    @PutMapping("/{missionId}")
    public ApiResponse<MissionResDTO.UpdateMission> updateMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.UpdateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_UPDATE;
        return ApiResponse.onSuccess(code, missionService.updateMission(missionId, dto));
    }

    // 미션 삭제
    @DeleteMapping("/{missionId}")
    public ApiResponse<Void> deleteMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_DELETE;
        missionService.deleteMission(missionId);
        return ApiResponse.onSuccess(code, null);
    }
}
