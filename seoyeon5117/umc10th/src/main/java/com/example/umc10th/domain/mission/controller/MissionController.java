package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "미션")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 생성
    @Operation(summary = "가게 미션 생성", description = "가게의 미션을 생성합니다.")
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.CreateMission> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_CREATE;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션 목록 조회
    @Operation(summary = "가게 내 미션 목록 조회", description = "가게의 미션 목록을 조회합니다.")
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSIONS_GET;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, cursor, query));
    }

    // 특정 미션 조회
    @GetMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.GetMission> getMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET;
        return ApiResponse.onSuccess(code, missionService.getMission(missionId));
    }

    // 미션 수정
    @PutMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.UpdateMission> updateMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.UpdateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_UPDATE;
        return ApiResponse.onSuccess(code, missionService.updateMission(missionId, dto));
    }

    // 미션 삭제
    @DeleteMapping("/v1/missions/{missionId}")
    public ApiResponse<Void> deleteMission(
            @PathVariable Long missionId
    ) {
        BaseSuccessCode code = MissionSuccessCode.MISSION_DELETE;
        missionService.deleteMission(missionId);
        return ApiResponse.onSuccess(code, null);
    }
}
