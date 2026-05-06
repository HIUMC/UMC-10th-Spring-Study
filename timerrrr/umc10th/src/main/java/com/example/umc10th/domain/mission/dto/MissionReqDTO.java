package com.example.umc10th.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    // 도전 가능 미션 목록 조회
    public record GetAvailableMissions(
            Long addressId,
            LocalDate lastDeadline,
            Long lastMissionId,
            int size
    ) {}

    // 내 미션 목록 조회 (진행중 / 진행 완료)
    public record GetMyMissions(
            String status,
            LocalDate lastDeadline,
            Long lastMissionId,
            int size
    ) {}

    // 미션 성공 누르기
    public record CompleteMission(
            Boolean missionComplete
    ) {}
}
