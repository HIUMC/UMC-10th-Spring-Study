package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 도전 가능 미션 목록 조회
    @GetMapping("/missions")
    public ApiResponse<List<MissionResDTO.MissionInfo>> getAvailableMissions(
            @RequestParam Long addressId,
            @RequestParam(defaultValue = "available") String status
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getAvailableMissions(new MissionReqDTO.GetAvailableMissions(addressId)));
    }

    // 내 미션 목록 조회 (진행중 / 진행 완료)
    @GetMapping("/members/{memberId}/missions")
    public ApiResponse<List<MissionResDTO.MyMissionInfo>> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam String status
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(memberId, new MissionReqDTO.GetMyMissions(status)));
    }

    // 미션 성공 누르기
    @PatchMapping("/members/{memberId}/missions/{missionId}")
    public ApiResponse<Void> completeMission(
            @PathVariable Long memberId,
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.CompleteMission dto
    ) {
        BaseSuccessCode code = GeneralSuccessCode.OK;
        missionService.completeMission(memberId, missionId, dto);
        return ApiResponse.onSuccess(code, null);
    }
}
