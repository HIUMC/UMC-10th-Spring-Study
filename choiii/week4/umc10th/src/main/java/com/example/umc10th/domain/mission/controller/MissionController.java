package com.example.umc10th.domain.mission.controller;


import com.example.umc10th.domain.mission.dto.request.MissionCreateRequest;
import com.example.umc10th.domain.mission.dto.request.UserMissionCompleteRequest;
import com.example.umc10th.domain.mission.dto.request.UserMissionCreateRequest;
import com.example.umc10th.domain.mission.dto.response.MissionResponse;
import com.example.umc10th.domain.mission.dto.response.UserMissionResponse;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.api.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/missions")
    public ApiResponse<MissionResponse> createMission(@RequestBody @Valid MissionCreateRequest request) {
        return ApiResponse.created(missionService.createMission(request));
    }

    @PostMapping("/users/{userId}/missions")
    public ApiResponse<UserMissionResponse> challengeMission(@PathVariable Long userId, @RequestBody @Valid UserMissionCreateRequest request) {
        return ApiResponse.created(missionService.challengeMission(userId, request));
    }

    @PatchMapping("/user-missions/{userMissionId}/complete")
    public ApiResponse<UserMissionResponse> completeMission(@PathVariable Long userMissionId, @RequestBody @Valid UserMissionCompleteRequest request) {
        return ApiResponse.ok(missionService.completeMission(userMissionId, request));
    }

    @GetMapping("/users/{userId}/missions")
    public ApiResponse<List<UserMissionResponse>> getUserMissions(@PathVariable Long userId) {
        return ApiResponse.ok(missionService.getUserMissions(userId));
    }
}
