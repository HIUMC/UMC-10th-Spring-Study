package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    // 미션 성공 누르기
    public record CompleteMission(
            Boolean missionComplete
    ) {}
}
