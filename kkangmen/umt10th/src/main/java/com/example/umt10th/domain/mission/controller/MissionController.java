package com.example.umt10th.domain.mission.controller;

import com.example.umt10th.domain.mission.dto.MissionReqDTO;
import com.example.umt10th.domain.mission.dto.MissionResDTO;
import com.example.umt10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umt10th.domain.mission.service.MissionService;
import com.example.umt10th.global.apiPayload.ApiResponse;
import com.example.umt10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /***
     * 내가 완료한/진행 중인 미션 조회
     * @param status
     * @return
     */
    @PostMapping("/v1/members/me/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @RequestBody MissionReqDTO.GetMission dto,
            @RequestParam boolean status,
            @RequestParam Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyMissions(dto, status, pageSize, cursor));
    }

    /***
     * 가게 미션 성공 누르기
     * @param missionId
     * @return
     */
    @PatchMapping("/v1/members/me/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionSuccessDto> successMission(
            @PathVariable("missionId") Long missionId
    ){
        BaseSuccessCode successCode = MissionSuccessCode.OK_2;
        return ApiResponse.onSuccess(successCode, missionService.succeedMission(missionId));
    }

    /***
     * 가게 미션 생성
     */
    @PostMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
            ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    /***
     * 가게 내 미션들 조회
     */
    @GetMapping("/v1/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable("storeId") Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, cursor, query));
    }
}
