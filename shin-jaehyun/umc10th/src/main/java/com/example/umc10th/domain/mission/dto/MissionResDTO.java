package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    // 미션 목록 조회
    @Builder
    public record Missions(
            String storeName,
            String foodCategory,
            String content,
            Long reward,
            LocalDate deadline
    ) {}

    // 달성한 미션 개수 조회
    @Builder
    public record MissionsCount(
            Long count
    ) {}
}
