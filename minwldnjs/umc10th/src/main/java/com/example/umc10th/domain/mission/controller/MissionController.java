package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 홈화면 미션 목록 조회
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeMissions(
            @RequestParam String region, @RequestParam int page) {
        return ApiResponse.onSuccess(missionService.getHomeMissions(region, page));
    }

    // 미션 성공 누르기 (상태 변경)
    @PatchMapping("/member-missions/{memberMissionId}")
    public ApiResponse<String> completeMission(@PathVariable Long memberMissionId) {
        return ApiResponse.onSuccess(memberMissionId + "번 미션 완료 처리");
    }
}