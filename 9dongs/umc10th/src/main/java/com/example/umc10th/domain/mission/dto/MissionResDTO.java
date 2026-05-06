package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import java.util.List;

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

    @Builder
    public record MissionDetailDTO(
            Long missionId,
            String restaurantName,
            Integer price,
            Integer reward,
            String deadline
    ) {
    }

    @Builder
    public record AvailableMissionListDTO(
            List<MissionDetailDTO> missions,
            Long nextCursor,
            Boolean hasNext
    ) {
    }

    @Builder
    public record MyMissionDetailDTO(
            Long memberMissionId,
            Long missionId,
            String restaurantName,
            Integer reward,
            String status
    ) {
    }

    @Builder
    public record MyMissionListDTO(
            List<MyMissionDetailDTO> myMissions,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}
