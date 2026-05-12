package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.store.entity.FoodCategory;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 미션 목록 조회
    @Builder
    public record getMissions(
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

    @Builder
    public record Pagination<T> (
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}
}
