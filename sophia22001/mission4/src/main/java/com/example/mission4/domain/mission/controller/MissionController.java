package com.example.mission4.domain.mission.controller;

import com.example.mission4.domain.mission.dto.MissionReqDTO;
import com.example.mission4.domain.mission.dto.MissionResDTO;
import com.example.mission4.domain.mission.exception.code.MissionSuccessCode;
import com.example.mission4.domain.mission.service.MissionService;
import com.example.mission4.global.apiPayload.ApiResponse;
import com.example.mission4.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

//    // 미션 목록 조회 (진행 중/ 진행 완료)
    @GetMapping("/missions")
    public ApiResponse<List<MissionResDTO.GetMissions>> getMissions(
            @RequestParam Boolean isCompleted,
            @RequestParam Long memberId) {

        BaseSuccessCode code = MissionSuccessCode.MISSIONS_FOUND;
        return ApiResponse.onSuccess(code, missionService.getMissions(isCompleted, memberId));

    }

    // 미션 성공 요청
    @PostMapping("/stores/{storeId}/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionComplete> missionComplete(
            @PathVariable Long storeId,
            @PathVariable Long missionId,
            @RequestParam Long memberId
    ) {

        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETED;
        return ApiResponse.onSuccess(code, missionService.missionComplete(storeId, missionId, memberId));
    }


}
