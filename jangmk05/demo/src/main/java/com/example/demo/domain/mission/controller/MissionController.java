package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionReqDTO;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.exception.code.MissionSuccessCode;
import com.example.demo.domain.mission.service.MissionService;
import com.example.demo.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회
    @GetMapping("/my/missions")
    public ApiResponse<MissionResDTO.MyMissionPreviewListDTO> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam MissionStatus status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_FOUND,
                missionService.getMyMissions(memberId, status, page)
        );
    }

    // 수행 가능한 미션 목록 조회
    @GetMapping("/missions/available")
    public ApiResponse<MissionResDTO.AvailableMissionListDTO> getAvailableMissions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_FOUND,
                missionService.getAvailableMissions(memberId, page)
        );
    }

    // 가게 미션 생성
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ) {
        MissionSuccessCode code = MissionSuccessCode.MISSION_CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션들 조회
    @GetMapping("/v1/store/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        MissionSuccessCode code = MissionSuccessCode.MISSION_FOUND;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId,pageSize, cursor, query));
    }
}