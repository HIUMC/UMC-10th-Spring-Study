package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {


    @Builder
    public record MissionDTO(
            Long missionId,
            String content,
            Long reward,
            LocalDate deadline
    ) {}

    @Builder
    public record MissionListDTO(
            List<MissionDTO> missionList,
            Long lastMissionId,
            Boolean hasNext
    ) {}

}
