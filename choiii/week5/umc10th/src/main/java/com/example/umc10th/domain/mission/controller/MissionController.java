package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.common.enums.MissionStatus;
import com.example.umc10th.domain.mission.dto.request.MissionCreateRequest;
import com.example.umc10th.domain.mission.dto.request.UserMissionCompleteRequest;
import com.example.umc10th.domain.mission.dto.request.UserMissionCreateRequest;
import com.example.umc10th.domain.mission.dto.response.MissionResponse;
import com.example.umc10th.domain.mission.dto.response.UserMissionResponse;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.api.ApiResponse;
import com.example.umc10th.global.api.code.CommonSuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/missions")
    public ApiResponse<MissionResponse> createMission(@RequestBody @Valid MissionCreateRequest request) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.CREATED,
                missionService.createMission(request)
        );
    }

    @PostMapping("/users/{userId}/missions")
    public ApiResponse<UserMissionResponse> challengeMission(@PathVariable Long userId, @RequestBody @Valid UserMissionCreateRequest request) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.CREATED,
                missionService.challengeMission(userId, request)
        );
    }

    @PatchMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<UserMissionResponse> completeMission(@PathVariable Long userMissionId, @RequestBody @Valid UserMissionCompleteRequest request) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.OK,
                missionService.completeMission(userMissionId, request)
        );
    }

    @GetMapping("/users/{userId}/missions")
    public ApiResponse<Page<UserMissionResponse>> getUserMissions(
            @PathVariable Long userId,
            @RequestParam(required = false) MissionStatus status,
            Pageable pageable
    ) {
        return ApiResponse.onSuccess(
                CommonSuccessCode.OK,
                missionService.getUserMissions(userId, status, pageable)
        );
    }
}
