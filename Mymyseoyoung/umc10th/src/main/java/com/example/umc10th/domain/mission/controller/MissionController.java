package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionRequestDTO;
import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    //사용자 미션 목록 조회 (status 별로 구분 )
    @GetMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissionList(@RequestParam(name = "status", required = false) String status)
    {
        //MISSION_FOUND successcode 추가
        return ApiResponse.onSuccess(null,null);
    }

    //사용자 미션 단건 조회
    @GetMapping("/missions/{missionId}")
    public ApiResponse<MissionResponseDTO.MissionDetailDTO> getMissionDetail(
            @PathVariable(name = "missionId") Long missionId) {

        return ApiResponse.onSuccess(null, null);
    }

    //미션 지역 조회
    @GetMapping("/missions/region")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissionsByRegion(
            @RequestParam(name = "regionId") Long regionId,
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        MissionResponseDTO.MissionListDTO response = missionService.getAvailableMissions(regionId, memberId, page);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_FOUND, response);
    }

    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")

    public ApiResponse<Void> createMission(@PathVariable Long storeId, @RequestBody @Valid MissionRequestDTO.CreateMission request) {
        BaseSuccessCode code=MissionSuccessCode.MISSION_CREATED;

        return ApiResponse.onSuccess(code, missionService.createMission(storeId,request));
    }

    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.Pagination<MissionResponseDTO.GetMission>> getMissions
            (@PathVariable Long storeId,
             @RequestParam Integer pageSize,
             @RequestParam String cursor,
             @RequestParam String query )
    {
        BaseSuccessCode code=MissionSuccessCode.MISSION_FOUND;
        return ApiResponse.onSuccess(code,missionService.getStoreMissions(storeId,pageSize,cursor,query));
    }
}
