package com.example.umc10th.domain.region.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class RegionResponseDTO {
    // 지역 변경 결과
    @Builder
    public record UpdateRegionResultDTO(
            Long memberId,
            String regionName,
            LocalDateTime updatedAt
    ) {}

    // 진행률 표시 응답
    @Builder
    public record GetProgressDTO(
            String regionName,
            Double progressPercentage, // 75.5% 등
            Integer completedMissions,
            Integer totalMissions
    ) {}
}
