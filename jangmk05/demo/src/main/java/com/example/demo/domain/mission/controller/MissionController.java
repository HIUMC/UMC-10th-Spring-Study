package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.exception.code.MissionSuccessCode;
import com.example.demo.domain.mission.service.MissionService;
import com.example.demo.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회
    @GetMapping("/my")
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
    @GetMapping("/available")
    public ApiResponse<MissionResDTO.AvailableMissionListDTO> getAvailableMissions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_FOUND,
                missionService.getAvailableMissions(memberId, page)
        );
    }
}