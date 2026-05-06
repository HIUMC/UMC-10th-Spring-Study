package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 미션 목록 조회
    @GetMapping("/v1/members/me/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissions(
            @ModelAttribute MissionReqDTO.MissionListRequest dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissionList(dto));
    }

    // 미션 성공 누르기
    @PatchMapping("/v1/members/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionCompleteDTO> completeMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionCompleteRequest dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.completeMission(missionId, dto));
    }
}
