package com.example.umc10th.domain.home.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    @Builder
    public record HomeResponseDTO(
            RegionDTO currentRegion,
            Integer clearedMissionCount,
            List<MissionPreviewDTO> missionList
    ) {
    }

    // 현재 지역
    @Builder
    public record RegionDTO(
            Long regionId,
            String regionName
    ) {
    }

    // 홈 미션 목록
    @Builder
    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            LocalDate deadline,
            Integer point
    ) {
    }
}
