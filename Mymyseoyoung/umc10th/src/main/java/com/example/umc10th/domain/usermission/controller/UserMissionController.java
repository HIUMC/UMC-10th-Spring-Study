package com.example.umc10th.domain.usermission.controller;


import com.example.umc10th.domain.user.dto.MemberRequestDTO;
import com.example.umc10th.domain.usermission.dto.UserMissionResponseDTO;
import com.example.umc10th.domain.usermission.service.UserMissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/missions")
@RequiredArgsConstructor
public class UserMissionController {
    private final UserMissionService userMissionService;

    //미션 도전 신청
    @PostMapping("/{missionId}")
    public ApiResponse<UserMissionResponseDTO.ChallengeResultDTO> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MemberRequestDTO.GetInfo userDto) {

      //Mission_challenged 이거 successcode 추가

        return ApiResponse.onSuccess(null, null);
    }

    //미션 성공 처리
    @PatchMapping("/{missionId}")
    public ApiResponse<UserMissionResponseDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody MemberRequestDTO.GetInfo userDto) {

        // TODO: UserMissionSuccessCode.MISSION_COMPLETED 추가 후 사용
        return ApiResponse.onSuccess(null, null);
    }
}
