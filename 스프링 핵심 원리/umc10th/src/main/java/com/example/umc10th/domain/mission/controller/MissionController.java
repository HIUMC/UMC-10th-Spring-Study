package com.example.umc10th.domain.mission.controller;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MissionController {
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeMissions(
            @RequestParam String region, @RequestParam String status, @RequestParam int page) {
        return ApiResponse.onSuccess(null);
    }
    @PatchMapping("/member-missions/{memberMissionId}")
    public ApiResponse<String> completeMission(@PathVariable Long memberMissionId) {
        return ApiResponse.onSuccess(memberMissionId + "번 미션 완료 처리");
    }
}
