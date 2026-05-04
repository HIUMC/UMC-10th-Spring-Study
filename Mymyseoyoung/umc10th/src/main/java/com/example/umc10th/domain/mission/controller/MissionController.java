package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.MissionResponseDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    //사용자 미션 목록 조회 (status 별로 구분 )
    @GetMapping
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissionList(@RequestParam(name = "status", required = false) String status)
    {
        //MISSION_FOUND successcode 추가
        return ApiResponse.onSuccess(null,null);
    }

    //사용자 미션 단건 조회
    @GetMapping("/{missionId}")
    public ApiResponse<MissionResponseDTO.MissionDetailDTO> getMissionDetail(
            @PathVariable(name = "missionId") Long missionId) {

        return ApiResponse.onSuccess(null, null);
    }

    //미션 지역 조회
    @GetMapping("/region")
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissionsByRegion(
            @RequestParam(name = "regionId") Long regionId,
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        MissionResponseDTO.MissionListDTO response = missionService.getAvailableMissions(regionId, memberId, page);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_FOUND, response);
    }

}
