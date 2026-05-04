package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.store.entity.FoodCategory;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public record MissionCursor(
            LocalDateTime lastCreatedAt,
            Long lastMissionId
    ) {}
}
