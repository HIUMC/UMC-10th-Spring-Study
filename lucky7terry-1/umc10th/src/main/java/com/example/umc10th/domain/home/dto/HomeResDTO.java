package com.example.umc10th.domain.home.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    @Builder
    public record HomeResponseDTO(
            RegionDTO currentRegion,
            Long clearedMissionCount,
            List<MissionPreviewDTO> missionList,
            Boolean hasNext,
            Long nextCursor
    ) {
    }

    @Builder
    public record RegionDTO(
            Long regionId,
            String locateName
    ) {
    }

    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            String category,
            LocalDate deadline,
            Integer point
    ) {
    }
}
