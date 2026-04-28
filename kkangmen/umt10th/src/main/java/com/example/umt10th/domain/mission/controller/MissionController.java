package com.example.umt10th.domain.mission.controller;

import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umt10th.domain.mission.service.MissionService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 미션 진행 중, 진행 완료 목록 조회
    @GetMapping("/members/me/missions")
    public ApiResponse<MissionResDTO.MissionListDto> getMissionList(
            @RequestParam("status1") String inProgress,
            @RequestParam("status2") String completed,
            @RequestParam("role") String role
    ){
        BaseSuccessCode successCode = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(successCode, missionService.getMissionList(inProgress, completed, role));
    }

    // 미션 성공
    @PatchMapping("/members/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionSuccessDto> successMission(
            @PathVariable("missionId") Long missionId
    ){
        BaseSuccessCode successCode = MissionSuccessCode.OK_2;
        return ApiResponse.onSuccess(successCode, missionService.succeedMission(missionId));
    }
}
