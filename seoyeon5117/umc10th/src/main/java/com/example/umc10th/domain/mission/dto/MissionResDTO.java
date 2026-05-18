package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record CreateMission(
            Long missionId
    ) {}

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String minPrice,
            LocalDateTime due_date
    ) {}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

    @Builder
    public record UpdateMission(
            Long missionId
    ) {}
}
