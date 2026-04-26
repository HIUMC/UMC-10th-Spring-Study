package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.Status;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 지역 미션 조회
    @GetMapping("/v1/missions")
    public ApiResponse<List<MissionResDTO.Missions>> getMissionsByRegion(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLng,
            @RequestParam Double maxLng
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissionsByRegion(minLat, maxLat, minLng, maxLng));
    }

    // 달성한 미션 개수 조회
    @GetMapping("/v1/missoins/my/count")
    public ApiResponse<MissionResDTO.MissionsCount> getCompletedMissionCount(

    ) {
        Long userId = 1L;

        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getCompletedMissionCount(userId));
    }

    // 나의 미션 목록 조회
    @GetMapping("/v1/missions/my")
    public ApiResponse<List<MissionResDTO.Missions>> getMyMissions(
            @RequestParam(required = false) Status status
    ) {
        Long userId = 1L;

        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(userId, status));
    }
}
