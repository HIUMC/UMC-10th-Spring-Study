package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MemberMissionStatus;

public class MissionReqDTO {

    public record StartMissionDTO(
            Long missionId
    ) {
    }

    public record UpdateStatusDTO(
            MemberMissionStatus status
    ) {
    }
}
