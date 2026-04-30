package com.example.umc10th.domain.mission.dto;

public class MissionReqDTO {

    public record CreateMission(
            Long storeId,
            String content, // 미션 내용
            Integer point
    ) {
    }

    public record UpdateMissionStatus(
            Long missionId
    ) {
    }

    public record UpdateMission(
            String content,
            Integer point
    ) {
    }
}
