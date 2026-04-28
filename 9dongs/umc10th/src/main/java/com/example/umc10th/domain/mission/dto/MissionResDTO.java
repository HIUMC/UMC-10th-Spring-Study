package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

public class MissionResDTO {

    @Builder
    public record MissionProgressSummaryDTO(
            Integer currentMissions,
            Integer targetMissions,
            Integer totalRewardPoint
    ) {
    }

    @Builder
    public record MissionStatusResultDTO(
            Long memberMissionId,
            String updatedStatus
    ) {
    }

    @Builder
    public record AuthCodeResultDTO(
            Long memberMissionId,
            String authCode
    ) {
    }
}
