package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    /* ───────────────────────────────────────────────────────────────
       지역미션 조회  GET /api/mission/{region}?page=&size=
       ─────────────────────────────────────────────────────────────── */
    @GetMapping("/{region}")
    public ApiResponse<MissionResDTO.RegionMissionResDTO> getMissionsByRegion(
            @PathVariable String region,
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        MissionResDTO.RegionMissionResDTO response = missionService.getMissionsByRegion(region, memberId, page, size);
        return ApiResponse.onSuccess(response);
    }

    /* ───────────────────────────────────────────────────────────────
       내 미션 조회  GET /api/mission/mission-challenge/me?memberId=&page=&size=
       ─────────────────────────────────────────────────────────────── */
    @GetMapping("/mission-challenge/me")
    public ApiResponse<MissionResDTO.MyMissionResDTO> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        MissionResDTO.MyMissionResDTO response = missionService.getMyMissions(memberId, page, size);
        return ApiResponse.onSuccess(response);
    }

    /* ───────────────────────────────────────────────────────────────
       미션 도전  POST /api/mission/{missionId}/mission-challenge?memberId=
       ─────────────────────────────────────────────────────────────── */
    @PostMapping("/{missionId}/mission-challenge")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MissionResDTO.MissionChallengeResDTO> challengeMission(
            @PathVariable Long missionId,
            @RequestParam Long memberId) {

        MissionResDTO.MissionChallengeResDTO response = missionService.challengeMission(missionId, memberId);
        return ApiResponse.onSuccess(response);
    }
}
