package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 도전 가능 미션 조회
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.AvailableMissionListDTO> getAvailableMissions(
            @RequestParam(name = "status", required = true) MissionStatus status,
            @RequestParam(name = "regionId", required = true) Long regionId,
            @RequestParam(name = "cursor", defaultValue = "0") Long cursor,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        List<Mission> missions = missionService.getAvailableMissions(regionId, status, cursor, size);
        MissionResDTO.AvailableMissionListDTO result = MissionConverter.toAvailableMissionListDTO(missions, size);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 미션 진행도 조회 ( 홈 화면 상단 )
    @GetMapping("/members/me/missions/progress")
    public ApiResponse<MissionResDTO.MissionProgressSummaryDTO> getMissionProgressSummary() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 미션 시작
    @PostMapping("/members/me/missions")
    public ApiResponse<MissionResDTO.MissionStatusResultDTO> startMission(
            @RequestBody MissionReqDTO.StartMissionDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }

    // 미션 목록 조회
    @GetMapping("/members/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(name = "status", required = true) MemberMissionStatus status,
            @RequestParam(name = "cursor", defaultValue = "0") Long cursor,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        Long memberId = 1L;
        List<MemberMission> myMissions = missionService.getMyMissions(memberId, status, cursor, size);
        MissionResDTO.MyMissionListDTO result = MissionConverter.toMyMissionListDTO(myMissions, size);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 내 미션 상태 업데이트
    @PatchMapping("/members/me/missions/{member_mission_id}")
    public ApiResponse<MissionResDTO.MissionStatusResultDTO> updateMissionStatus(
            @PathVariable(name = "member_mission_id") Long memberMissionId,
            @RequestBody MissionReqDTO.UpdateStatusDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 미션 성공 요청
    @PostMapping("/members/me/missions/{member_mission_id}/auth-code")
    public ApiResponse<MissionResDTO.AuthCodeResultDTO> requestAuthCode(
            @PathVariable(name = "member_mission_id") Long memberMissionId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }
}
