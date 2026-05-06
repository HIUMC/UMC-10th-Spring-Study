package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    public record MissionListDTO(
            List<MissionPreviewDTO> missionList

    ){}

    public record MissionCompleteDTO(
            Long missionId
    ){}

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            Integer point,
            String deadline,
            LocalDateTime completedAt
    ){}
}
