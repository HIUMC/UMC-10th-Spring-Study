package com.example.umc10th.domain.member.controller;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    @PostMapping("/auth/users")
    public ApiResponse<String> join(@RequestBody MemberReqDTO.JoinDTO request) {
        return ApiResponse.onSuccess("회원가입 성공");
    }
    @GetMapping("/api/users/me/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(@RequestParam String status) {
        return ApiResponse.onSuccess(null);
    }
}
