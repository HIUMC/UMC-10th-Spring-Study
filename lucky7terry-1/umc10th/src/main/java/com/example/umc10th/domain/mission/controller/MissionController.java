package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/v1/members/me/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @RequestBody MissionReqDTO.GetMission dto,
            @RequestParam Boolean status,
            @RequestParam Integer pageSize,
            @RequestParam String cursor
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(dto, status, pageSize, cursor));
    }

    // 미션 성공 누르기
    @PatchMapping("/v1/members/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionCompleteDTO> completeMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId));
    }

    // 가게 미션 생성
    @PostMapping("v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션 조회
    @GetMapping("v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getStoreMissions(storeId, pageSize, cursor, query));
    }
}
