package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionListDTO(
            List<MissionPreviewDTO> missionList,
            Boolean hasNext,
            Long nextCursor
    ) {
    }

    @Builder
    public record MissionPreviewDTO(
            Long memberMissionId,
            Long missionId,
            String storeName,
            Integer point,
            String status,
            String content,
            LocalDate deadline
    ) {
    }

    @Builder
    public record MissionCompleteDTO(
            Long missionId,
            Boolean isCompleted
    ) {
    }
}

