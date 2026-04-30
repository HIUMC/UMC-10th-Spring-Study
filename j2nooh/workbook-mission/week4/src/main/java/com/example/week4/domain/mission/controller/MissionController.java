package com.example.week4.domain.mission.controller;

import com.example.week4.domain.mission.dto.MissionReqDTO;
import com.example.week4.domain.mission.dto.MissionResDTO;
import com.example.week4.domain.mission.entity.Mission;
import com.example.week4.domain.mission.exception.code.MissionSuccessCode;
import com.example.week4.domain.mission.service.MissionService;
import com.example.week4.global.apiPayload.ApiResponse;
import com.example.week4.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;

    // 특정 가게의 미션 조회
    @PostMapping("/v1/missions/store")
    public ApiResponse<MissionResDTO.MissionListResponse> getStoreMissions(
            @RequestBody @Valid MissionReqDTO.StoreMissionListRequest dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_STORE_MISSIONS;
        MissionResDTO.MissionListResponse response = missionService.getStoreMissions(dto);

        return ApiResponse.onSuccess(code, response);
    }

    // 유저 (진행중/완료) 미션 조회
    @PostMapping("/v1/user-missions")
    public ApiResponse<MissionResDTO.UserMissionListResponse> getUserMissions(
            @RequestBody @Valid MissionReqDTO.UserMissionListRequest dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.GET_USER_MISSIONS;
        MissionResDTO.UserMissionListResponse response = missionService.getUserMissions(dto);

        return ApiResponse.onSuccess(code, response);
    }

    // 미션 도전
    @PostMapping("/v1/user-missions/challenge")
    public ApiResponse<MissionResDTO.UserMissionResponse> challengeMission(
            @RequestBody @Valid MissionReqDTO.ChallengeMissionRequest dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.CHALLENGE_MISSION;
        MissionResDTO.UserMissionResponse response = missionService.challengeMission(dto);

        return ApiResponse.onSuccess(code, response);
    }
}
