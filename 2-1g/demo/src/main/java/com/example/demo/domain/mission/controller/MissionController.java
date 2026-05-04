package com.example.demo.domain.mission.controller;

import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionRequestDTO;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import com.example.demo.domain.mission.entity.MemberMission;
import com.example.demo.domain.mission.enums.MissionStatus;
import com.example.demo.domain.mission.service.MissionService;
import global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MissionController {
    // GET 요청의 여러 query parameter를 한 번에 받기 위해 @ModelAttribute DTO 바인딩 방식을 사용했다.
    // 동작은 @RequestParam과 같지만, 5주차 과제에서 DTO 형태를 더 분명히 보여주기 쉽다.

    private final MissionService missionService;

    @GetMapping("/home/missions")
    public ApiResponse<MissionResponseDTO.MissionListResultDTO> getAvailableMissions(
            @ModelAttribute MissionRequestDTO.HomeMissionQueryRequest request
    ) {
        MissionResponseDTO.MissionPreviewDTO preview = MissionResponseDTO.MissionPreviewDTO.builder()
                .memberMissionId(null)
                .missionId(1L)
                .storeName("요아정 구로점")
                .missionDescription("요거트 아이스크림 인증하기")
                .point(500L)
                .deadline(LocalDate.now().plusDays(7))
                .status(MissionStatus.CHALLENGING)
                .build();

        MissionResponseDTO.MissionListResultDTO response = MissionResponseDTO.MissionListResultDTO.builder()
                .missionList(List.of(preview))
                .page(request.getPage())
                .size(request.getSize())
                .totalElements(1L)
                .totalPages(1)
                .build();

        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/member-mission")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
            @RequestBody MissionRequestDTO.ChallengeMissionRequest request
    ) {
        MissionResponseDTO.ChallengeMissionResultDTO response = MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .memberMissionId(1L)
                .missionId(request.getMissionId())
                .status(MissionStatus.CHALLENGING)
                .challengedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionListResultDTO> getMemberMissions(
            @ModelAttribute MissionRequestDTO.MemberMissionQueryRequest request
    ) {

        Page<MemberMission> memberMissions = missionService.getMemberMissions(1L, request.getStatus(), request.getPage());

        MissionResponseDTO.MissionListResultDTO response = MissionConverter.toMissionListResultDTO(memberMissions);

        return ApiResponse.onSuccess(response);
    }

    @PatchMapping("/member-missions/{completionId}")
    public ApiResponse<MissionResponseDTO.CompleteMissionResultDTO> completeMission(
            @PathVariable Long completionId,
            @RequestBody MissionRequestDTO.CompleteMissionRequest request
    ) {
        MissionResponseDTO.CompleteMissionResultDTO response = MissionResponseDTO.CompleteMissionResultDTO.builder()
                .memberMissionId(completionId)
                .status(request.getStatus())
                .completedAt(LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(response);
    }
}
