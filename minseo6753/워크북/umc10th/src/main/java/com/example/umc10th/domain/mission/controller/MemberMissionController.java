package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/missions")
public class MemberMissionController {

    private final MissionService missionService;

    @GetMapping("/")
    public ApiResponse<MissionResDTO.InfoList> getMemberMissions(
            //토큰
            @RequestParam MissionStatus status
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMemberMissions(status)
        );
    }
}
