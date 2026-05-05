package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    // 도전 가능 미션 목록 조회
    @Builder
    public record MissionInfo(
            Long missionId,
            String missionDetail,
            LocalDate deadline,
            Integer successPoint
    ) {}

    // 내 미션 목록 조회 (진행중 / 진행 완료)
    @Builder
    public record MyMissionInfo(
            Long memberMissionId,
            String missionDetail,
            LocalDate deadline,
            Integer successPoint,
            Boolean missionComplete
    ) {}
}
