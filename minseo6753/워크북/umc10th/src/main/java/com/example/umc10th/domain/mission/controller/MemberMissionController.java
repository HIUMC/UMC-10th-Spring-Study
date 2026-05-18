package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.service.MemberMissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/missions")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;

    @GetMapping()
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.Info>> getMemberMissions(
            @RequestHeader Long memberId, //todo 토큰인증으로 변경
            @RequestParam MissionStatus status,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.getMemberMissions(memberId, status, pageSize, pageNumber)
        );
    }

    @PatchMapping("/{missionId}")
    public ApiResponse<MissionResDTO.Info> updateMemberMission(
            //토큰
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.Status request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                memberMissionService.updateMemberMission(missionId, request)
        );
    }
}
