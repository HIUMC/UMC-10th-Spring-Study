package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionReqDTO;
import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.service.MissionService;
import com.example.demo.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMissionList(
            @ModelAttribute MissionReqDTO.MissionQueryDTO request
    ) {
        return ApiResponse.onSuccess(missionService.getMissionList(request));
    }

    @PatchMapping("/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable Long missionId,
            @Validated @RequestBody MissionReqDTO.MissionCompleteDTO request
    ) {
        return ApiResponse.onSuccess(missionService.completeMission(missionId, request));
    }

    @GetMapping("/home")
    public ApiResponse<MissionResDTO.HomeDTO> getHome() {
        return ApiResponse.onSuccess(missionService.getHome());
    }
}
