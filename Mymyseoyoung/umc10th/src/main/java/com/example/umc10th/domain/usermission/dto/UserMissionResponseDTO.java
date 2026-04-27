package com.example.umc10th.domain.usermission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserMissionResponseDTO {
    // 미션 도전 신청 결과
    @Builder
    public record ChallengeResultDTO(
            Long userMissionId,
            Long missionId,
            LocalDateTime createdAt
    ) {}

    // 미션 성공 처리 결과
    @Builder
    public record MissionCompleteResultDTO(
            Long userMissionId,
            String status, // "COMPLETE"
            LocalDateTime updatedAt
    ) {}
}
