package com.example.umt10th.domain.mission.controller;

import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umt10th.domain.mission.service.MissionService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseErrorCode;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /***
     * 미션 완료 목록 조회
     * @param isCompleted
     * @return
     */
    @GetMapping("/v1/members/me/missions")
    public ApiResponse<MissionResDTO.MissionListDto> getMissionList(
            @RequestParam("isCompleted") Boolean isCompleted,
            @RequestParam(value = "cursor", defaultValue = "0") Long cursor
    ){
        BaseSuccessCode successCode = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(successCode, missionService.getMissionList(isCompleted, "o", cursor));
    }

    // 미션 성공
    @PatchMapping("/v1/members/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionSuccessDto> successMission(
            @PathVariable("missionId") Long missionId
    ){
        BaseSuccessCode successCode = MissionSuccessCode.OK_2;
        return ApiResponse.onSuccess(successCode, missionService.succeedMission(missionId));
    }
}
