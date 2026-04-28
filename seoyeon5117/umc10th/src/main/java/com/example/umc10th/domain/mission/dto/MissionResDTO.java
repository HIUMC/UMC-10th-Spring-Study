package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record CreateMission(
            Long missionId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record GetMission(
            Long missionId,
            Long storeId,
            String content,
            Integer point,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record UpdateMission(
            Long missionId,
            LocalDateTime updatedAt
    ) {}
}
