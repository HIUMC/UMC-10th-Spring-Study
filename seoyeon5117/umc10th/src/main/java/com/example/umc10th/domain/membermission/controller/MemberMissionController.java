package com.example.umc10th.domain.membermission.controller;

import com.example.umc10th.domain.membermission.dto.MemberMissionReqDTO;
import com.example.umc10th.domain.membermission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.membermission.enums.MemberMissionStatus;
import com.example.umc10th.domain.membermission.enums.MemberMissionSuccessCode;
import com.example.umc10th.domain.membermission.service.MemberMissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "멤버 미션")
public class MemberMissionController {

    private final MemberMissionService memberMissionService;


    // 홈 화면 지역별 내 미션 목록 조회(IN_PROGRESS)
    @Operation(summary = "홈 화면 지역별 내 미션 목록 조회", description = "IN_PROGRESS 상태인 지역별 내 미션 목록 조회합니다.")
    @GetMapping("/v1/members/{memberId}/missions/home") // TODO: memberId -> me로 변경 필요
    public ApiResponse<MemberMissionResDTO.GetHomeMemberMissions> getHomeMemberMissions(
            @PathVariable Long memberId,
            @RequestParam String address,
            @RequestParam Pageable pageable
    ) {
        BaseSuccessCode code = MemberMissionSuccessCode.MEMBER_MISSION_GET;

        return ApiResponse.onSuccess(code, memberMissionService.getHomeMemberMissions(memberId, address, pageable));
    }

    // 미션 페이지 - 내 미션 목록 조회 (IN_PROGRESS / COMPLETED)
    @Operation(summary = "내 미션 목록 조회", description = "미션 상태(IN_PROGRESS/COMPLETED)를 이용하여 내 미션 목록 조회합니다.")
    @GetMapping("/v1/members/{memberId}/missions") // TODO: memberId -> me로 변경 필요
    public ApiResponse<MemberMissionResDTO.Pagination<MemberMissionResDTO.GetMemberMission>> getMemberMissions(
            @PathVariable Long memberId,
            @RequestParam MemberMissionStatus status,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MemberMissionSuccessCode.MEMBER_MISSION_GET;

        return ApiResponse.onSuccess(code, memberMissionService.getMemberMissionsByStatus(memberId, status, pageSize, pageNumber, sort));
    }

    // 미션 성공 처리
    @PatchMapping("/v1/members/{memberId}/missions/{missionId}") // TODO: memberId -> me로 변경 필요
    public ApiResponse<MemberMissionResDTO.UpdateMemberMissionStatus> completeMission(
            @PathVariable Long missionId,
            @RequestBody MemberMissionReqDTO.CompleteMission dto
    ) {
        BaseSuccessCode code = MemberMissionSuccessCode.MEMBER_MISSION_COMPLETE;
        return ApiResponse.onSuccess(code, memberMissionService.completeMission(missionId, dto));
    }
}
