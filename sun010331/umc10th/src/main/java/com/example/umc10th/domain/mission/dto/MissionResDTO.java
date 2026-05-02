package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {


    @Builder
    public record MissionDTO(
            Long missionId,
            String title,
            String content,
            Integer point
    ) {}

    @Builder
    public record MissionListDTO(
            List<MissionDTO> missionList,
            Long lastMissionId,
            Boolean hasNext
    ) {}

}
