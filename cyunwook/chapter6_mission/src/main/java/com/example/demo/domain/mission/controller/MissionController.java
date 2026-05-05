package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.dto.MissionResDTO;
import com.example.demo.domain.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/members/{memberId}/missions")
    @Operation(summary = "내 미션 조회 (진행중/완료)")
    public ResponseEntity<MissionResDTO.MissionPageDTO> getMyMissions(
            @PathVariable Long memberId,
            @RequestParam Boolean isComplete,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(missionService.getMyMissions(memberId, isComplete, page, size));
    }

    @GetMapping("/missions")
    @Operation(summary = "홈 화면 - 지역별 미션 목록")
    public ResponseEntity<MissionResDTO.MissionPageDTO> getMissionsByLocation(
            @RequestParam Long locationId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(missionService.getMissionsByLocation(locationId, page, size));
    }
}
