package umc10th.assignment.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResponseDto {
    @Builder
    public record MissionInfo(
            Long missionId,
            String title,
            String content,
            Integer reward,
            String status
    ) {
    }

    @Builder
    public record MissionList(
            List<MissionInfo> missions
    ) {
    }

    @Builder
    public record MissionDetail(
            Long missionId,
            String title,
            String content,
            Integer reward,
            String status
    ) {
    }

    @Builder
    public record CompleteMission(
            Long missionId,
            String status
    ) {
    }
}
